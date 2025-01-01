package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import com.example.asignment.Entity.Category;
import com.example.asignment.Entity.Source;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class SettingController {
    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableView<Source> sourceTable;

    @FXML
    private TableColumn<Category, String> colName1; // Cột cho tên danh mục
    @FXML
    private TableColumn<Category, Void> colAction1;
    @FXML
    private TableColumn<Source, String> colName2; // Cột cho tên nguồn
    @FXML
    private TableColumn<Source, Void> colAction2; // Cột cho hành động

    private final ObservableList<Category> categories = FXCollections.observableArrayList();
    private final ObservableList<Source> sources = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configureColumns(); // Cấu hình các cột
        loadCategoriesIntoTable(); // Tải dữ liệu vào TableView
        loadSourcesIntoTable(); // Tải dữ liệu nguồn vào TableView
    }

    private void configureColumns() {
        colName1.setCellValueFactory(new PropertyValueFactory<>("name")); // Thiết lập ánh xạ cho cột name
        colAction1.setCellFactory(param -> new TableCell<>() {
            private final Button actionButton = new Button("Edit");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item != null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(actionButton);
                    actionButton.setOnAction(event -> {
                        Category selectedCategory = getTableView().getItems().get(getIndex());
                        editCategory(selectedCategory);
                    });
                }
            }
        });

        colName2.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAction2.setCellFactory(param -> new TableCell<>() {
            private final Button actionButton = new Button("Edit");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item != null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(actionButton);
                    actionButton.setOnAction(event -> {
                        Source selectedSource = getTableView().getItems().get(getIndex());
                        editSource(selectedSource);
                    });
                }
            }
        });
    }

    private void loadCategoriesIntoTable() {
        String sysPath = System.getProperty("user.dir");
        String dataFile1 = sysPath.replace("/", "\\") + "\\src\\main\\java\\com\\example\\asignment\\Database\\category.txt";
        System.out.println(dataFile1);

        List<Category> categoryList = loadCategoriesFromFile(dataFile1);
        categories.clear();
        categories.addAll(categoryList);
        categoryTable.setItems(categories); // Cập nhật dữ liệu cho categoryTable
    }

    private void loadSourcesIntoTable() {
        String sysPath = System.getProperty("user.dir");
        String dataFile2 = sysPath.replace("/", "\\") + "\\src\\main\\java\\com\\example\\asignment\\Database\\source.txt"; // Giả định bạn có một file riêng cho nguồn
        System.out.println(dataFile2);

        List<Source> sourceList = loadSourcesFromFile(dataFile2);
        sources.clear();
        sources.addAll(sourceList);
        sourceTable.setItems(sources); // Cập nhật dữ liệu cho sourceTable
    }

    private List<Category> loadCategoriesFromFile(String filePath) {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";"); // Giả định các giá trị được phân tách bằng dấu chấm phẩy
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim()); // Chuyển đổi chuỗi thành int
                    String name = parts[1].trim(); // Lấy tên danh mục
                    categories.add(new Category(id, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + categories.size() + " categories");
        return categories;
    }

    private List<Source> loadSourcesFromFile(String filePath) {
        List<Source> sources = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";"); // Giả định các giá trị được phân tách bằng dấu chấm phẩy
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim()); // Chuyển đổi chuỗi thành int
                    String name = parts[1].trim(); // Lấy tên nguồn
                    sources.add(new Source(id, name)); // Sửa đổi để thêm vào danh sách sources
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + sources.size() + " sources");
        return sources;
    }
    private void editCategory(Category category) {

        Dialog<Category> dialog = new Dialog<>();
        dialog.setTitle("Edit Category");


        Label nameLabel = new Label("Name Category:");
        TextField nameField = new TextField(category.getName());

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);


        VBox vbox = new VBox(10, nameLabel, nameField);
        dialog.getDialogPane().setContent(vbox);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                category.setName(nameField.getText());
                return category;
            }
            return null;
        });

        dialog.showAndWait();
        categoryTable.refresh();
    }
    private void editSource(Source source) {

        Dialog<Source> dialog = new Dialog<>();
        dialog.setTitle("Edit Category");


        Label nameLabel = new Label("Name Source:");
        TextField nameField = new TextField(source.getName());

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);


        VBox vbox = new VBox(10, nameLabel, nameField);
        dialog.getDialogPane().setContent(vbox);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                source.setName(nameField.getText());
                return source;
            }
            return null;
        });

        dialog.showAndWait();
        sourceTable.refresh();
    }
}
