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
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.ReserveBO;
import lk.ijse.hostal.bo.custom.UserBO;
import lk.ijse.hostal.entity.User;
import lk.ijse.hostal.util.NavigateUtil;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginContext;
    public TextField txtUserName;
    public TextField txtPassword;
    public PasswordField pwdPassword;
    public ImageView visibilityOn;
    public ImageView visibiltyOff;
    public TextField txtUserId;
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public void initialize(){
        visibiltyOff.setVisible(true);
       txtPassword.setVisible(false);




    }
    public void loginOnAction(ActionEvent actionEvent) throws Exception {
        String id=txtUserId.getText();
       // String password=txtPassword.getText();

        User user=userBO.searchUser(id);

        if (user!=null) {
            if (txtPassword.getText().equals(user.getPassword()) || pwdPassword.getText().equals(user.getPassword())) {
                NavigateUtil.newUi(loginContext, "DashboardForm");
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect Password..!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Incorrect User Id..!").show();
        }

    }

    public void btnOnMouseEntered(MouseEvent mouseEvent) {
        visibiltyOff.setVisible(false);
        pwdPassword.setVisible(false);
        txtPassword.setVisible(true);
        txtPassword.setText(pwdPassword.getText());
    }

    public void btnOnMouseExited(MouseEvent mouseEvent) {
        visibiltyOff.setVisible(true);
        pwdPassword.setVisible(true);
        txtPassword.setVisible(false);
    }


    public void shutDownOnMouseClicked(MouseEvent mouseEvent) {

        NavigateUtil.closeForm(loginContext);
    }

    }

