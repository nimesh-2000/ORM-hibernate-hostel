package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.RoomBO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.tm.RoomTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class RoomsFormController {
    public AnchorPane roomContext;
    public JFXTextField txtRoomId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXComboBox<String> cmbRoomType;
    public JFXButton btnRoomSave;
    public TableView<RoomTM> tblRoom;

    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    public JFXButton btnRoomDelete;

    public void initialize() {
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));


        initUI();

        ObservableList<String> obl = FXCollections.observableArrayList();

        obl.add("Non-AC");
        obl.add("Non-AC / Food");
        obl.add("AC ");
        obl.add("AC / Food");

        cmbRoomType.setItems(obl);

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRoomDelete.setDisable(newValue == null);
            btnRoomSave.setText(newValue != null ? "Update" : "Save");
            btnRoomSave.setDisable(newValue == null);

            if (newValue != null) {
                txtRoomId.setText(newValue.getRoom_type_id());
                cmbRoomType.setValue(newValue.getType());
                txtKeyMoney.setText(newValue.getKey_money().setScale(2).toString());
                txtQty.setText(newValue.getQty() + "");


                txtRoomId.setDisable(false);
                cmbRoomType.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtQty.setDisable(false);

            }
        });


        loadAllRooms();
    }

    private void loadAllRooms() {
        tblRoom.getItems().clear();

        try {
            List<RoomDTO> allRooms = roomBO.getAllRoom();
            for (RoomDTO room : allRooms) {
                tblRoom.getItems().add(new RoomTM(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtRoomId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQty.clear();
        txtRoomId.setDisable(true);
        cmbRoomType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtQty.setDisable(true);
        btnRoomSave.setDisable(true);
        btnRoomDelete.setDisable(true);
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {
        txtRoomId.setDisable(false);
        cmbRoomType.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQty.setDisable(false);

        txtRoomId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQty.clear();

        txtRoomId.requestFocus();
        btnRoomSave.setDisable(false);
        btnRoomSave.setText("Save");
        tblRoom.getSelectionModel().clearSelection();
    }

    public void btnRoomSaveOnAction(ActionEvent actionEvent) {
        String roomTypeId = txtRoomId.getText();
        String roomType = (String) cmbRoomType.getValue();
        BigDecimal keyMoney = new BigDecimal(txtKeyMoney.getText()).setScale(2);
        int roomsQty = Integer.parseInt(txtQty.getText());


        if (!roomTypeId.matches("^(RM-)[0-9]{2,4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
            txtRoomId.requestFocus();
            return;
        } else if (!txtKeyMoney.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid KeyMoney").show();
            txtKeyMoney.requestFocus();
            return;
        }else if (!txtQty.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Qty").show();
            txtQty.requestFocus();
            return;
        }
        try {
            if (btnRoomSave.getText().equalsIgnoreCase("save")) {

                if (roomBO.saveRoom(new RoomDTO(roomTypeId, roomType, keyMoney, roomsQty))) {
                    tblRoom.getItems().add(new RoomTM(roomTypeId, roomType, keyMoney, roomsQty));
                    initUI();

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed!").show();
                }


            } else {

                roomBO.updateRoom(new RoomDTO(roomTypeId, roomType, keyMoney, roomsQty));

                RoomTM selectedRoom = tblRoom.getSelectionModel().getSelectedItem();
                selectedRoom.setRoom_type_id(roomTypeId);
                selectedRoom.setType(roomType);
                selectedRoom.setKey_money(keyMoney);
                selectedRoom.setQty(roomsQty);
                tblRoom.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                initUI();

            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    public void btnDeleteOnaction(ActionEvent actionEvent) {
        String id = tblRoom.getSelectionModel().getSelectedItem().getRoom_type_id();
        try {
            roomBO.deleteRoom(id);
            tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
            tblRoom.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }
}
