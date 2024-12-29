package com.example.asignment.Controller;

import com.example.asignment.Entity.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private AnchorPane homeTabContent;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "1234".equals(password)) {
            System.out.println("Login successful!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/personal-budget-view.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.setTitle("Personal Budget");
            currentStage.setScene(dashboardScene);
            currentStage.show();


        } else {
            System.out.println("Invalid credentials!");
        }
    }
}
