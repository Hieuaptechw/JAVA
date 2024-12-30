package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import com.example.asignment.Entity.Category;
import com.example.asignment.Entity.TransactionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseController {
    @FXML
    private MenuButton categoryMenuButton;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtDescription;
    @FXML
    private TableView<Budget> expensesTable;

    @FXML
    private TableColumn<Budget,Void> colAction;

    private final ObservableList<Budget> budgetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up columns as before
        TableColumn<Budget, LocalDate> colDate = (TableColumn<Budget, LocalDate>) expensesTable.getColumns().get(0);
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Budget, String> colCategory = (TableColumn<Budget, String>) expensesTable.getColumns().get(1);
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Budget, Double> colAmount = (TableColumn<Budget, Double>) expensesTable.getColumns().get(2);
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Budget, String> colNote = (TableColumn<Budget, String>) expensesTable.getColumns().get(3);
        colNote.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add Edit and Delete buttons
        colAction.setCellFactory(column -> new TableCell<Budget, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                // Edit Button Logic
                editButton.setOnAction(event -> {
                    Budget selectedBudget = getTableView().getItems().get(getIndex());
                    editBudget(selectedBudget); // Call method to populate the form for editing
                });

                // Delete Button Logic
                deleteButton.setOnAction(event -> {
                    Budget selectedBudget = getTableView().getItems().get(getIndex());
                    deleteBudget(selectedBudget); // Call method to remove the selected budget
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(10, editButton, deleteButton);
                    setGraphic(hbox);
                }
            }
        });

        expensesTable.setItems(budgetList);
        expensesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadCategoriesIntoMenu();
    }

    private void loadCategoriesIntoMenu() {
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath.replace("/", "\\") + "\\src\\main\\java\\com\\example\\asignment\\Database\\category.txt";
        System.out.println(dataFile);
        List<Category> categories = loadCategoriesFromFile(dataFile);
        categoryMenuButton.getItems().clear();
        for (Category category : categories) {
            MenuItem menuItem = new MenuItem(category.getName());
            menuItem.setOnAction(event -> {
                categoryMenuButton.setText(category.getName());
                categoryMenuButton.setUserData(category.getId());
                System.out.println("Selected category: " + category.getName());
            });
            categoryMenuButton.getItems().add(menuItem);
        }
    }

    private List<Category> loadCategoriesFromFile(String filePath) {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    categories.add(new Category(id, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(categories);
        return categories;
    }

    @FXML
    private void handleAddButtonAction() {
        LocalDate date = txtDate.getValue();
        String category = categoryMenuButton.getText();
        System.out.println(category);
        String  description = txtDescription.getText();
        double amount;
        try {
            amount = Double.parseDouble(txtAmount.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
            return;
        }

        int id = budgetList.size();
        Budget newBudget = new Budget(id, category, amount, description, date, TransactionType.EXPENSE);
        System.out.println(newBudget);
        budgetList.add(newBudget);
        expensesTable.setItems(budgetList);
        txtDate.setValue(null);
        txtAmount.clear();
        txtDescription.clear();
        categoryMenuButton.setText("Category");
        showAlert("Success", "Add Expenses successfully!");
    }
    @FXML
    private void handleClearButtonAction(){
        txtDescription.clear();
        txtAmount.clear();
        txtDate.setValue(null);
        categoryMenuButton.setText("Category");
    }
    private void editBudget(Budget budget) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/asignment/View/edit-view.fxml"));
            AnchorPane root = loader.load();
            EditController editBudgetController = loader.getController();
            editBudgetController.setSelectedBudget(budget);
            Stage stage = new Stage();
            stage.setTitle("Edit Budget");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteBudget(Budget budget) {
        budgetList.remove(budget);
        expensesTable.setItems(budgetList);
        showAlert("Success", "Deleted successfully!");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setMinWidth(350);
        alertStage.setMinHeight(150);
        alertStage.setWidth(350);
        alertStage.setHeight(150);
        alert.showAndWait();
    }
}
