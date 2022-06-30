package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.hostal.bo.custom.UserBO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.UserDTO;
import lk.ijse.hostal.tm.StudentTM;
import lk.ijse.hostal.tm.UserTM;

import java.sql.SQLException;
import java.util.List;

public class UserFormController {
    public AnchorPane userContext;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<UserTM> tblUser;
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public JFXTextField txtUserId;

    public void initialize() {
        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        initUI();


        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtUserId.setText(newValue.getUserId());
                txtUserName.setText(newValue.getUserName());
                txtPassword.setText(newValue.getPassword());



                txtUserId.setDisable(false);
                txtUserName.setDisable(false);
                txtPassword.setDisable(false);
            }
        });

        loadAllUser();
    }

    private void loadAllUser() {
        tblUser.getItems().clear();
        try {
            List<UserDTO> allUser = userBO.getAllUser();
            for (UserDTO user : allUser) {
                tblUser.getItems().add(new UserTM(user.getUserId(), user.getUserName(), user.getPassword()));
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
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();

        txtUserId.setDisable(true);
        txtUserName.setDisable(true);
        txtPassword.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtUserId.setDisable(false);
        txtUserName.setDisable(false);
        txtPassword.setDisable(false);

        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();

        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblUser.getSelectionModel().clearSelection();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String userId = txtUserId.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if (!userId.matches("^(S00-)[0-9]{3,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Id").show();
            txtUserId.requestFocus();
            return;
        } else if (!userName.matches("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Name").show();
            txtUserName.requestFocus();
            return;
        }


            try {
                if (btnSave.getText().equalsIgnoreCase("save")) {
                    if (userBO.saveUser(new UserDTO(userId, userName, password))) {
                        tblUser.getItems().add(new UserTM(userId, userName, password));
                        initUI();

                        new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed!").show();
                    }


                } else {
                    userBO.updateUser(new UserDTO(userId, userName, password));

                    UserTM selectedUser = tblUser.getSelectionModel().getSelectedItem();
                    selectedUser.setUserId(userId);
                    selectedUser.setUserName(userName);
                    selectedUser.setPassword(password);
                    initUI();
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                    tblUser.refresh();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }


    public void btnDeleteOnaction(ActionEvent actionEvent) {
        String id = tblUser.getSelectionModel().getSelectedItem().getUserId();
        try {
            userBO.deleteUser(id);
            tblUser.getItems().remove(tblUser.getSelectionModel().getSelectedItem());
            tblUser.getSelectionModel().clearSelection();
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

