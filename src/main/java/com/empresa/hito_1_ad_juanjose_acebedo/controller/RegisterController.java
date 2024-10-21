package com.empresa.hito_1_ad_juanjose_acebedo.controller;

import com.empresa.hito_1_ad_juanjose_acebedo.Repository.UserDAO;
import com.empresa.hito_1_ad_juanjose_acebedo.entities.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button alreadyHaveAccountButton;

    @FXML
    protected void onRegisterButtonClick() {
        String name = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        if (!isValidEmail(email)) {
            showAlert("Error", "Invalid email format!");
            return;
        }

        UserDAO userDAO = new UserDAO();
        if (userDAO.userExists(email)) {
            showAlert("Error", "User already exists!");
            return;
        }

        UserEntity user = new UserEntity(name, email, password);
        userDAO.saveUser(user);

        showAlert("Success", "User registered successfully!");
        switchToLogin();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    @FXML
    private void switchToLogin() {
        try {
            Parent loginView = FXMLLoader.load(getClass().getResource("/com/empresa/hito_1_ad_juanjose_acebedo/Views/Login_view.fxml"));
            Scene loginScene = new Scene(loginView);

            Stage currentStage = (Stage) alreadyHaveAccountButton.getScene().getWindow();
            currentStage.setScene(loginScene);
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