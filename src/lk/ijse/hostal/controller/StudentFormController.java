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
import lk.ijse.hostal.bo.custom.StudentBO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentFormController {
    public AnchorPane studentContext;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDOB;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnSave;
    public TableView<StudentTM> tblStudent;
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    public JFXButton btnDelete;
    public AnchorPane keyMoneyContext;
    public TableView tblKeyMoney;

    public void initialize() {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        initUI();

        ObservableList<String> obl = FXCollections.observableArrayList();

        obl.add("Male");
        obl.add("Female");

        cmbGender.setItems(obl);


        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtStudentId.setText(newValue.getStudentId());
                txtStudentName.setText(newValue.getStudentName());
                txtAddress.setText(newValue.getAddress());
                txtContactNo.setText(newValue.getContactNo());
                txtDOB.setText(newValue.getDob());
                cmbGender.setValue(newValue.getGender());
                

                txtStudentId.setDisable(false);
                txtStudentName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtDOB.setDisable(false);
                cmbGender.setDisable(false);
                
            }
        });

        loadAllStudent();
    }

    private void loadAllStudent() {
        tblStudent.getItems().clear();
        try {
            List<StudentDTO> allStudent = studentBO.getAllStudent();
            for (StudentDTO student : allStudent) {
                tblStudent.getItems().add(new StudentTM(student.getStudent_id(), student.getStudentName(), student.getStudentAddress(), student.getContact_no(), student.getDob(), student.getGender()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtDOB.clear();
        cmbGender.getSelectionModel().clearSelection();

        txtStudentId.setDisable(true);
        txtStudentName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtDOB.setDisable(true);
        cmbGender.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtStudentId.setDisable(false);
        txtStudentName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtDOB.setDisable(false);


        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtDOB.clear();

        cmbGender.requestFocus();
        cmbGender.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudent.getSelectionModel().clearSelection();
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {
        String studentId = txtStudentId.getText();
        String studentName = txtStudentName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        String dob = txtDOB.getText();
        String gender = (String) cmbGender.getValue();
        if (!studentId.matches("^(S00-)[0-9]{3,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Id").show();
            txtStudentName.requestFocus();
            return;
        } else if (!studentName.matches("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Name").show();
            txtStudentName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        }else if (!contactNo.matches("^07(7|6|8|1|2|5|0|4)-[0-9]{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number").show();
            txtContactNo.requestFocus();
            return;
        }else if (!dob.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Date Of Birth").show();
            txtDOB.requestFocus();
            return;
        }

        //("^(C00-)[0-9]{3,5}$") id
        //("^07(7|6|8|1|2|5|0|4)-[0-9]{7}$"); contact
        //^\d{4}-\d{2}-\d{2}$ dob

        try {
            if (btnSave.getText().equalsIgnoreCase("save")) {
                if (studentBO.saveStudent(new StudentDTO(studentId, studentName, address, contactNo, dob, gender))) {
                    tblStudent.getItems().add(new StudentTM(studentId, studentName, address, contactNo, dob, gender));
                    initUI();

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed!").show();
                }


            } else {
                studentBO.updateStudent(new StudentDTO(studentId, studentName, address, contactNo, dob, gender));

                StudentTM selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
                selectedStudent.setStudentId(studentId);
                selectedStudent.setStudentName(studentName);
                selectedStudent.setAddress(address);
                selectedStudent.setContactNo(contactNo);
                selectedStudent.setDob(dob);
                selectedStudent.setGender(gender);
                initUI();
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
                tblStudent.refresh();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    public void btnDeleteOnaction(ActionEvent actionEvent) {
        String id = tblStudent.getSelectionModel().getSelectedItem().getStudentId();
        try {
            studentBO.deleteStudent(id);
            tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
            tblStudent.getSelectionModel().clearSelection();
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
