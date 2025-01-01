package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import com.example.asignment.Entity.Category;
import com.example.asignment.Entity.TransactionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncomeController {
    @FXML
    private MenuButton categoryMenuButton;
    @FXML
    private DatePicker txtFrom;
    @FXML
    private DatePicker txtTo;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TableView<Budget> expensesTable;
    @FXML
    private RadioButton radBubble;

    @FXML
    private RadioButton radInsert;
    @FXML
    private TableColumn<Budget,Void> colAction;

    private final ObservableList<Budget> budgetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        TableColumn<Budget, LocalDate> colDate = (TableColumn<Budget, LocalDate>) expensesTable.getColumns().get(0);
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        centerColumn(colDate);

        TableColumn<Budget, String> colCategory = (TableColumn<Budget, String>) expensesTable.getColumns().get(1);
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        centerColumn(colCategory);

        TableColumn<Budget, Double> colAmount = (TableColumn<Budget, Double>) expensesTable.getColumns().get(2);
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        centerColumn(colAmount);

        TableColumn<Budget, String> colNote = (TableColumn<Budget, String>) expensesTable.getColumns().get(3);
        colNote.setCellValueFactory(new PropertyValueFactory<>("description"));
        centerColumn(colNote);

        colAction.setCellFactory(column -> new TableCell<Budget, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setOnAction(event -> {
                    Budget selectedBudget = getTableView().getItems().get(getIndex());
                    editBudget(selectedBudget);
                });


                deleteButton.setOnAction(event -> {
                    Budget selectedBudget = getTableView().getItems().get(getIndex());
                    deleteBudget(selectedBudget);
                });


                editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(10, editButton, deleteButton);
                    hbox.setAlignment(Pos.CENTER);
                    setGraphic(hbox);
                }
            }
        });


        expensesTable.setItems(budgetList);
        expensesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        radBubble.setOnAction(event -> handleRadioButtonSelection(radBubble));
        radInsert.setOnAction(event -> handleRadioButtonSelection(radInsert));
        loadCategoriesIntoMenu();
    }


    private <T> void centerColumn(TableColumn<Budget, T> column) {
        column.setCellFactory(col -> new TableCell<Budget, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }

    private void loadCategoriesIntoMenu() {
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath.replace("/", "\\") + "\\src\\main\\java\\com\\example\\asignment\\Database\\source.txt";
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
        Budget newBudget = new Budget(id, category, amount, description, date, TransactionType.INCOME);
        System.out.println(newBudget);
        budgetList.add(newBudget);
        expensesTable.setItems(budgetList);
        txtDate.setValue(null);
        txtAmount.clear();
        txtDescription.clear();
        categoryMenuButton.setText("Category");
        exportToFile();
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

            EditController editController = loader.getController();
            editController.setSelectedBudget(budget);

            Stage stage = new Stage();
            stage.setTitle("Edit Budget");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            expensesTable.refresh();
            exportToFile();
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
    @FXML
    private void handleSearchAction() {
        String searchText = txtSearch.getText().toLowerCase();
        System.out.println("Search button clicked! Searching for: " + searchText);

        FilteredList<Budget> filteredData = new FilteredList<>(budgetList, budget -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseCategory = budget.getCategory().toLowerCase();
            String lowerCaseDescription = budget.getDescription().toLowerCase();
            String amount = String.valueOf(budget.getAmount());
            return lowerCaseCategory.contains(searchText) || lowerCaseDescription.contains(searchText) || amount.contains(searchText);
        });
        System.out.println(filteredData.size());
        expensesTable.setItems(filteredData);
        if (filteredData.isEmpty()) {
            showAlert("No Data", "No data found!");
        } else {
            showAlert("Success", "Found " + filteredData.size() + " results.");
        }

        colAction.setCellFactory(column -> new TableCell<Budget, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setOnAction(event -> {
                    Budget selectedBudget = getTableView().getItems().get(getIndex());
                    editBudget(selectedBudget);
                });

                deleteButton.setOnAction(event -> {
                    Budget selectedBudget = getTableView().getItems().get(getIndex());
                    deleteBudget(selectedBudget);
                });

                editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(10, editButton, deleteButton);
                    hbox.setAlignment(Pos.CENTER);
                    setGraphic(hbox);
                }
            }
        });
    }

    @FXML
    private void handleFilterByDateAction() {
        LocalDate fromDate = txtFrom.getValue();
        LocalDate toDate = txtTo.getValue();

        if (fromDate == null || toDate == null) {
            showAlert("Error", "Please select both 'From' and 'To' dates.");
            return;
        }

        if (fromDate.isAfter(toDate)) {
            showAlert("Error", "'From' date cannot be after 'To' date.");
            return;
        }
        FilteredList<Budget> filteredData = new FilteredList<>(budgetList, budget -> {
            LocalDate recordDate = budget.getDate();
            return (recordDate.isEqual(fromDate) || recordDate.isAfter(fromDate)) &&
                    (recordDate.isEqual(toDate) || recordDate.isBefore(toDate));
        });

        expensesTable.setItems(filteredData);

        if (filteredData.isEmpty()) {
            showAlert("No Data", "No records found in the selected date range.");
        } else {
            showAlert("Success", "Found " + filteredData.size() + " results in the selected date range.");
        }
    }
    @FXML
    private void handleSortByDate() {
        if (radBubble.isSelected()) {
            bubbleSortByDate();
            showAlert("Sorted", "Sorted by date using Bubble Sort.");
        } else if (radInsert.isSelected()) {
            insertionSortByDate();
            showAlert("Sorted", "Sorted by date using Insertion Sort.");
        }
        expensesTable.setItems(budgetList);
    }

    @FXML
    private void handleSortByAmount() {
        if (radBubble.isSelected()) {
            bubbleSortByAmount();
            showAlert("Sorted", "Sorted by amount using Bubble Sort.");
        } else if (radInsert.isSelected()) {
            insertionSortByAmount();
            showAlert("Sorted", "Sorted by amount using Insertion Sort.");
        }
        expensesTable.setItems(budgetList);
    }

    private ObservableList<Budget> bubbleSortByDate() {
        ObservableList<Budget> sortedList = FXCollections.observableArrayList(budgetList);
        int n = sortedList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedList.get(j).getDate().isAfter(sortedList.get(j + 1).getDate())) {
                    Budget temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    private ObservableList<Budget> bubbleSortByAmount() {
        ObservableList<Budget> sortedList = FXCollections.observableArrayList(budgetList);
        int n = sortedList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedList.get(j).getAmount() > sortedList.get(j + 1).getAmount()) {
                    Budget temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    private ObservableList<Budget> insertionSortByDate() {
        ObservableList<Budget> sortedList = FXCollections.observableArrayList(budgetList);
        int n = sortedList.size();
        for(int i = 1;i<n;i++) {
            Budget current = sortedList.get(i);
            int j = i-1;
            while(j>=0 && sortedList.get(j).getDate().isAfter(current.getDate())){
                sortedList.set(j+1,sortedList.get(j));
                j--;
            }
            sortedList.set(j+1,current);
        }
        return sortedList;
    }

    private ObservableList<Budget> insertionSortByAmount() {
        ObservableList<Budget> sortedList = FXCollections.observableArrayList(budgetList);
        int n = sortedList.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortedList.get(j).getAmount() < sortedList.get(minIndex).getAmount()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Budget temp = sortedList.get(i);
                sortedList.set(i, sortedList.get(minIndex));
                sortedList.set(minIndex, temp);
            }
        }
        return sortedList;
    }
    private void handleRadioButtonSelection(RadioButton selected) {
        if (selected == radBubble) {
            radInsert.setSelected(false);
        } else if (selected == radInsert) {
            radBubble.setSelected(false);
        }
    }
    public void exportToFile() {
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath.replace("/", "\\") + "\\src\\main\\java\\com\\example\\asignment\\Database\\income.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(dataFile))) {
            for (Budget income : budgetList) {
                writer.println(income.getId() + ", " +
                        income.getCategory()+ ", " +
                        income.getAmount() + ", " +
                        income.getDate() + ", " +
                        income.getTransactionType() + ", " +
                        income.getDescription());
            }

            System.out.println("income exported to " + dataFile);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public void refresh() {
        initialize();
    }
}
