package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import com.example.asignment.Entity.Category;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditController {
    @FXML
    private DatePicker txtDate;

    @FXML
    private MenuButton categoryMenuButton;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDescription;

    private Budget selectedBudget;
    private List<Category> categories;
    @FXML
    public void initialize() {
        loadCategoriesIntoMenu();
    }

    public void setSelectedBudget(Budget budget) {
        this.selectedBudget = budget;
        txtDate.setValue(budget.getDate());
        categoryMenuButton.setText(budget.getCategory());
        txtAmount.setText(String.valueOf(budget.getAmount()));
        txtDescription.setText(budget.getDescription());
    }

    private void loadCategoriesIntoMenu() {
        // This is where you would load your categories from a file or a database
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath.replace("/", "\\") + "\\src\\main\\java\\com\\example\\asignment\\Database\\category.txt";
        categories = loadCategoriesFromFile(dataFile);

        categoryMenuButton.getItems().clear();
        for (Category category : categories) {
            MenuItem menuItem = new MenuItem(category.getName());
            menuItem.setOnAction(event -> {
                categoryMenuButton.setText(category.getName());
                categoryMenuButton.setUserData(category.getId());
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
    private void handleSaveButtonAction() {
        LocalDate date = txtDate.getValue();
        String category = categoryMenuButton.getText();
        String description = txtDescription.getText();
        double amount;

        try {
            amount = Double.parseDouble(txtAmount.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
            return;
        }
        if (category.equals("Category")) {
            System.out.println("Please select a category.");
            return;
        }

        selectedBudget.setDate(date);
        selectedBudget.setCategory(category);
        selectedBudget.setAmount(amount);
        selectedBudget.setDescription(description);
        Stage stage = (Stage) categoryMenuButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButtonAction() {
        Stage stage = (Stage) categoryMenuButton.getScene().getWindow();
        stage.close();
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
