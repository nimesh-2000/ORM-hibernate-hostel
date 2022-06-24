package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class StudentFormController {
    public AnchorPane studentContext;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDOB;
    public JFXComboBox cmbGender;
    public JFXButton btnSave;
    public TableView tblStudent;

    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnaction(ActionEvent actionEvent) {
    }
}
