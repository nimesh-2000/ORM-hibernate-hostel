package lk.ijse.hostal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostal.util.NavigateUtil;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginContext;
    public TextField txtUserName;
    public TextField txtPassword;
    public PasswordField pwdPassword;
    public ImageView visibilityOn;
    public ImageView visibiltyOff;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        NavigateUtil.newUi(loginContext, "DashboardForm");
    }

    public void btnOnMouseEntered(MouseEvent mouseEvent) {
    }

    public void btnOnMouseExited(MouseEvent mouseEvent) {
    }


    public void shutDownOnMouseClicked(MouseEvent mouseEvent) {
        NavigateUtil.closeForm(loginContext);
    }

    }

