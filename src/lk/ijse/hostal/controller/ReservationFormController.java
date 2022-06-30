package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.ReserveBO;
import lk.ijse.hostal.bo.custom.RoomBO;
import lk.ijse.hostal.dto.ReserveDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.tm.ReserveTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReservationFormController {
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVE);
    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    public AnchorPane registerContext;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtStatus;
    public JFXComboBox<String> cmbRoomId;
    public TableView<ReserveTM> tblRegister;
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtStudentName;
    public Label lblRoomId;
    public Label lblRoomType;
    public Label LblRoomQty;
    public JFXTextField txtReservationId;
    public Label lblDate;
    public Label lblAvailable;
    public TextField txtSearch;
    public JFXButton btnReservation;
    public JFXTextField txtQty;
    String reservationId;

    public void initialize() throws Exception {
        tblRegister.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        tblRegister.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblRegister.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblRegister.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblRegister.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblRegister.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));
        TableColumn<ReserveTM, Button> lastCol = (TableColumn<ReserveTM, Button>) tblRegister.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                if (tblRegister.getSelectionModel().getSelectedItem() != null) {
                    try {
                        reservationId = tblRegister.getSelectionModel().getSelectedItem().getReservationId();
                        if (reserveBO.deleteReservation(reservationId)) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted.....").show();
                            tblRegister.getItems().remove(param.getValue());
                            tblRegister.getSelectionModel().clearSelection();

                        } else {

                            new Alert(Alert.AlertType.ERROR, "Try Again.....").show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Select Row....").show();
                }
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        try {
            loadAllReservation();
        } catch (Exception e) {
            e.printStackTrace();
        }


        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    List<StudentDTO> all = reserveBO.searchStudent(newValue);
                    for (StudentDTO dto : all) {
                        txtStudentName.setText(dto.getStudentName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtStudentName.clear();
            }
        });


        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                try {
                    List<RoomDTO> all = reserveBO.searchRoom(newValue);
                    for (RoomDTO dto : all) {
                        lblRoomId.setText(dto.getRoom_type_id());
                        lblRoomType.setText(dto.getType());
                        txtRoomType.setText(dto.getType());
                        txtKeyMoney.setText(dto.getKey_money().setScale(2).toString());
                        Optional<ReserveTM> optOrderDetail = tblRegister.getItems().stream().filter(detail -> detail.getRoomId().equals(newValue)).findFirst();
                        LblRoomQty.setText((optOrderDetail.isPresent() ? dto.getQty() - optOrderDetail.get().getQty() : dto.getQty()) + "");
                        if (dto.getQty() > 0) {
                            lblAvailable.setText("Available");
                            lblAvailable.setTextFill(Color.web("#26ff00"));
                        } if(dto.getQty() == 0){
                            lblAvailable.setText("Not Available");
                            lblAvailable.setTextFill(Color.web("#e74c3c"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                txtRoomType.clear();
                txtKeyMoney.clear();
                txtQty.clear();
                LblRoomQty.setDisable(true);
                lblRoomId.setDisable(true);
                lblRoomType.setDisable(true);
            }
        });


        loadAllStudentId();
        loadAllRoomId();
        tblRegister.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                btnReservation.setText("Update");
                txtReservationId.setText(newValue.getReservationId());
                cmbStudentId.setValue(newValue.getStudentId());
                cmbRoomId.setValue(newValue.getRoomId());
                txtStatus.setText(newValue.getStatus());
                txtQty.setText(String.valueOf(newValue.getQty()));
            }else{
                cmbStudentId.getSelectionModel().clearSelection();
                cmbRoomId.getSelectionModel().clearSelection();
                txtQty.clear();
                txtStudentName.clear();
                txtStatus.clear();
                txtRoomType.clear();
                txtKeyMoney.clear();
            }
        });
        reservationId = generateNewReservationId();
        txtReservationId.setText( reservationId);
        lblDate.setText(LocalDate.now().toString());
    }

    private void loadAllRoomId() {
        try {
            List<RoomDTO> all = reserveBO.getAllRoom();
            for (RoomDTO dto : all) {
                cmbRoomId.getItems().add(dto.getRoom_type_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentId() throws Exception {
        try {
            List<StudentDTO> all = reserveBO.getAllStudent();
            for (StudentDTO dto : all) {
                cmbStudentId.getItems().add(dto.getStudent_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllReservation() throws Exception {
        ObservableList<ReserveTM> observableList = FXCollections.observableArrayList();
        List<ReserveDTO> list = reserveBO.getAllReserve();

        for (ReserveDTO r : list) {
            String reserveID = r.getResId();
            Room room = r.getRoomId();
            String roomID = room.getRoom_type_id();
            Student student = r.getStudentId();
            String studentID = student.getStudent_id();
            LocalDate date = r.getDate();
            String status = r.getStatus();
            int qty = r.getQty();



            ReserveTM reservationTM = new ReserveTM(reserveID, studentID, roomID, date, status, qty);
            observableList.add(reservationTM);
            tblRegister.setItems(observableList);
        }
    }

    public void BtnReservationOnAction(ActionEvent actionEvent) throws Exception {
        Student s1 = new Student();
        s1.setStudent_id(cmbStudentId.getValue());

        Room r1 = new Room();
        r1.setRoom_type_id(cmbRoomId.getValue());

        if (!btnReservation.getText().equals("Update")){
            reserveBO.save(new ReserveDTO(txtReservationId.getText(), LocalDate.now(), s1, r1, txtStatus.getText(), Integer.parseInt(txtQty.getText())));

            cmbStudentId.setValue(null);
            cmbRoomId.setValue(null);
            txtQty.clear();
            txtStatus.clear();

        }else if (btnReservation.getText().equals("Update")){
            btnReservation.setText("Reserved");
            reserveBO.UpdateReservation(new ReserveDTO(txtReservationId.getText(), LocalDate.now(), s1, r1, txtStatus.getText(), Integer.parseInt(txtQty.getText())));
        }
        loadAllReservation();
        reservationId = generateNewReservationId();
        txtReservationId.setText( reservationId);
    }




    public void searchOnKeyReleased(KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (txtSearch.getText().equals("")) {
                loadAllReservation();
            }else {
                System.out.println(txtSearch.getText());
                List<ReserveDTO> reserveDTOS = reserveBO.searchReserve(txtSearch.getText());
                tblRegister.getItems().clear();
                ObservableList<ReserveTM> reserveTMS = FXCollections.observableArrayList();

                for (ReserveDTO reserveDTO : reserveDTOS) {
                    System.out.println("for loop" + reserveDTO.getResId());
                    tblRegister.getItems().add(new ReserveTM(reserveDTO.getResId(),
                                    reserveDTO.getStudentId().getStudent_id(),
                                    reserveDTO.getRoomId().getRoom_type_id(),
                                    reserveDTO.getDate(),
                                    reserveDTO.getStatus(),
                                    reserveDTO.getQty())
                            // BigDecimal.valueOf(od.getTotal())
                    );

                }
            }


        }
    }
    private String generateNewReservationId() throws Exception {
        try {
            return reserveBO.getReservationId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new Reserve Id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "RES-001";
    }
}
