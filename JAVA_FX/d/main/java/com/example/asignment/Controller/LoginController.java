package com.example.asignment.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label labelUser;
    @FXML
    private Label labelPass;
    @FXML
    private Label errorLabel;
    @FXML
    private Button btnLogin;

    @FXML
    private MenuButton languageMenu;

    private Map<String, Map<String, String>> allTranslations;
    private Map<String, String> currentTranslations;

    @FXML
    public void initialize() {
        loadTranslations();
        setLanguage("en");
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "1234".equals(password)) {
            System.out.println("Login successful!");

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/personal-budget-view.fxml"));
                Scene dashboardScene = new Scene(fxmlLoader.load());

                Stage currentStage = (Stage) usernameField.getScene().getWindow();
                currentStage.setTitle(currentTranslations.get("dashboard_title"));

                currentStage.setScene(dashboardScene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setText(currentTranslations.get("login_error"));
        }
    }

    @FXML
    private void setLanguageToEnglish() {
        setLanguage("en");
        labelUser.setText(currentTranslations.get("username"));
        labelPass.setText(currentTranslations.get("password"));
        btnLogin.setText(currentTranslations.get("login"));


    }

    @FXML
    private void setLanguageToVietnamese() {
        setLanguage("vi");
        System.out.println("vi");
        labelUser.setText(currentTranslations.get("username"));
        labelPass.setText(currentTranslations.get("password"));
        btnLogin.setText(currentTranslations.get("login"));

    }

    private void setLanguage(String languageCode) {
        if (allTranslations != null && allTranslations.containsKey(languageCode)) {
            currentTranslations = allTranslations.get(languageCode);
            languageMenu.setText(currentTranslations.get("language"));
            errorLabel.setText("");
        }
    }

    private void loadTranslations() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath + "/src/main/java/com/example/asignment/Database/language.json";

        try {
            allTranslations = objectMapper.readValue(new File(dataFile), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
