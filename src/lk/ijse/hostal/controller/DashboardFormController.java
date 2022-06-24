package lk.ijse.hostal.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class DashboardFormController {
    public AnchorPane mainDashboardContext;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane dashboardContext;
    public void initialize() throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        dashboardContext.getChildren().add(parent);
        loadDateAndTime();
//        String userName=LoginFormController.userName;
//        lblUserName.setText(String.valueOf(userName));
//        setRotate(c1,true,360,10);
//        setRotate(c2,false,360,10);
    }

    private void loadDateAndTime() {
        Calendar clndr = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("aa");



        Timeline clock = new Timeline(new KeyFrame(Duration.INDEFINITE.ZERO, e -> {
            LocalTime currenttime = LocalTime.now();
            LocalDate currentdate = LocalDate.now();
            lblTime.setText(currenttime.getHour() + ":" + currenttime.getMinute() + ":" + currenttime.getSecond()+"  "+format1.format(clndr.getTime()));
            lblDate.setText(currentdate.getDayOfWeek()+","+ currentdate.getMonth() +" " + currentdate.getDayOfMonth() + "," + currentdate.getYear());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void registerOnAction(ActionEvent actionEvent) {
    }

    public void roomsOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RoomsForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        dashboardContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        dashboardContext.getChildren().add(parent);
    }
    private void setUi(String location) throws IOException {
        Stage stage=(Stage) mainDashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(DashboardFormController.class.getResource("../view/LoginForm.fxml"))));
        stage.centerOnScreen();
        stage.centerOnScreen();
        stage.show();
    }
}
