package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import com.example.asignment.Entity.Category;
import com.example.asignment.Entity.TransactionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    private TableView<Budget> expensesTable;

    private final ObservableList<Budget> budgetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Khởi tạo các cột trong TableView
        TableColumn<Budget, LocalDate> colDate = (TableColumn<Budget, LocalDate>) expensesTable.getColumns().get(0);
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Budget, String> colCategory = (TableColumn<Budget, String>) expensesTable.getColumns().get(1);
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Budget, Double> colAmount = (TableColumn<Budget, Double>) expensesTable.getColumns().get(2);
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Budget, String> colNote = (TableColumn<Budget, String>) expensesTable.getColumns().get(3);
        colNote.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Budget, Budget> colAction = new TableColumn<>("Action");
        colAction.setCellFactory(column -> new TableCell<Budget, Budget>() {
            final Button btn = new Button("Edit");

            @Override
            protected void updateItem(Budget item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        System.out.println("Edit clicked for: " + item);
                    });
                    setGraphic(btn); // Đặt nút vào ô
                    setText(null); // Xóa văn bản nếu có
                }
            }
        });

// Đặt chiều rộng tối thiểu cho cột Action
        colAction.setMinWidth(100);
        expensesTable.getColumns().add(colAction);

        // Đặt danh sách vào TableView
        expensesTable.setItems(budgetList);
        expensesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Tự động điều chỉnh kích thước cột

        loadCategoriesIntoMenu(); // Nạp danh mục vào MenuButton
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

        double amount;
        try {
            amount = Double.parseDouble(txtAmount.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
            return;
        }

        int id = budgetList.size();
        String description = "hieudz";
        Budget newBudget = new Budget(id, category, amount, description, date, TransactionType.EXPENSE);
        System.out.println(newBudget);
        budgetList.add(newBudget);
        expensesTable.setItems(budgetList); // Không cần thiết, vì bạn đã gán ở trên
        txtDate.setValue(null);
        txtAmount.clear();
        categoryMenuButton.setText("Category");
    }
}
