package com.example.asignment.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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

            // Tải FXML và tạo Scene mới
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/personal-budget-view.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());

            // Lấy Stage hiện tại (cửa sổ)
            Stage currentStage = (Stage) usernameField.getScene().getWindow();

            // Đặt title cho cửa sổ
            currentStage.setTitle("Personal Budget");

            // Cập nhật Scene mới
            currentStage.setScene(dashboardScene);

            // Căn cửa sổ mới vào giữa màn hình
            centerStage(currentStage);

            // Hiển thị cửa sổ
            currentStage.show();
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    // Phương thức để căn cửa sổ vào giữa màn hình
    private void centerStage(Stage stage) {
        // Lấy chiều rộng và chiều cao của màn hình
        double screenWidth = javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();

        // Lấy chiều rộng và chiều cao của cửa sổ
        double windowWidth = stage.getWidth();
        double windowHeight = stage.getHeight();

        // Tính toán tọa độ để căn cửa sổ ra giữa
        double x = (screenWidth - windowWidth) / 2;
        double y = (screenHeight - windowHeight) / 2;

        // Đặt vị trí cho cửa sổ
        stage.setX(x);
        stage.setY(y);
    }
}
