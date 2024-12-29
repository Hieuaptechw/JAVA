package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import com.example.asignment.Entity.Category;
import com.example.asignment.Entity.TransactionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private AnchorPane homeTabContent;
    @FXML
    private AnchorPane expensesTabContent;
    @FXML
    private AnchorPane incomeTabContent;
    @FXML
    private AnchorPane settingTabContent;
    @FXML
    public void initialize() throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/home-view.fxml"));
        AnchorPane homeView = homeLoader.load();
        homeTabContent.getChildren().setAll(homeView);

        FXMLLoader expensesLoader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/expenses-view.fxml"));
        AnchorPane expensesView = expensesLoader.load();
        expensesTabContent.getChildren().setAll(expensesView);

        FXMLLoader incomeLoader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/income-view.fxml"));
        AnchorPane incomeView = incomeLoader.load();
        incomeTabContent.getChildren().setAll(incomeView);

        FXMLLoader settingLoader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/setting-view.fxml"));
        AnchorPane settingView = settingLoader.load();
        settingTabContent.getChildren().setAll(settingView);

    }
}
