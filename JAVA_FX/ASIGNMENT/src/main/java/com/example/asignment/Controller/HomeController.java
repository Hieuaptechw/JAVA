package com.example.asignment.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private Label totalIncomeLabel;
    @FXML
    private Label totalExpenseLabel;
    @FXML
    private Label remainingBudgetLabel;

    // Initialize method to set data dynamically
    @FXML
    public void initialize() {
        // You can fetch these values from your database or logic layer
        totalIncomeLabel.setText("$5000"); // Example data
        totalExpenseLabel.setText("$2000"); // Example data
        remainingBudgetLabel.setText("$3000"); // Example data
    }

    // Event handlers
    public void handleAddTransaction() {
        System.out.println("Navigate to Add Transaction Page");
        // Logic to navigate to Add Transaction page
    }

    public void handleViewTransactions() {
        System.out.println("Navigate to View Transactions Page");
        // Logic to navigate to View Transactions page
    }

    public void handleStatistics() {
        System.out.println("Navigate to Statistics Page");
        // Logic to navigate to Statistics page
    }
}