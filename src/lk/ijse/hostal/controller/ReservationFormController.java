package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ReservationFormController {
    public AnchorPane registerContext;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtStatus;
    public JFXComboBox cmbRoomId;
    public TableView tblRegister;
    public JFXComboBox cmbStudentId;
    public JFXTextField txtStudentName;
    public Label lblRoomId;
    public Label lblRoomType;
    public Label LblRoomQty;
    public JFXTextField txtReservationId;
    public Label lblDate;
    public Label lblAvailable;
    public TextField txtSearch;

    public void BtnReservationOnAction(ActionEvent actionEvent) {
    }



    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }


    public void searchOnKeyReleased(KeyEvent keyEvent) {
    }
}
