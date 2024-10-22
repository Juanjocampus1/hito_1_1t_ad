package com.empresa.hito_1_ad_juanjose_acebedo.controller;

import com.empresa.hito_1_ad_juanjose_acebedo.Repository.Persistence;
import com.empresa.hito_1_ad_juanjose_acebedo.Repository.UserDAO;
import com.empresa.hito_1_ad_juanjose_acebedo.entities.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button back;
    @FXML
    private Button loginButton;

    @FXML
    protected void onLoginButtonClick() {
        String name = usernameField.getText();
        String password = passwordField.getText();

        if (authenticateUser(name, password)) {
            showAlert("Success", "Logged in successfully!");
            switchToMainView();
        } else {
            showAlert("Error", "Invalid name or password!");
        }
    }

    private boolean authenticateUser(String name, String password) {
        UserDAO userDAO = new UserDAO();
        List<UserEntity> users = userDAO.getAllUsers();
        for (UserEntity user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                Persistence.loggedInUsername = name;
                return true;
            }
        }
        return false;
    }

    @FXML
    protected void forgotPassword() {
        showAlert("Info", "Password recovery is not implemented yet.");
    }

    @FXML
    protected void back() {
        try {
            Parent registerView = FXMLLoader.load(getClass().getResource("/com/empresa/hito_1_ad_juanjose_acebedo/Views/Register_view.fxml"));
            Scene registerScene = new Scene(registerView);
            Stage currentStage = (Stage) back.getScene().getWindow();
            currentStage.setScene(registerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToMainView() {
        try {
            Parent registerView = FXMLLoader.load(getClass().getResource("/com/empresa/hito_1_ad_juanjose_acebedo/Views/Main_view.fxml"));
            Scene registerScene = new Scene(registerView);
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.setScene(registerScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}