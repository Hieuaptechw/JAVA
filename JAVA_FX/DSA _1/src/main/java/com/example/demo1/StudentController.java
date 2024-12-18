package com.example.demo1;

import com.example.demo1.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StudentController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtBirthday;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioFemale;
    @FXML
    private ComboBox<String> comboSort;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<Student> studentTable;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        TableColumn<Student, Integer> idColumn = (TableColumn<Student, Integer>) studentTable.getColumns().get(0);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameColumn = (TableColumn<Student, String>) studentTable.getColumns().get(1);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageColumn = (TableColumn<Student, Integer>) studentTable.getColumns().get(2);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Student, String> genderColumn = (TableColumn<Student, String>) studentTable.getColumns().get(3);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, LocalDate> birthdayColumn = (TableColumn<Student, LocalDate>) studentTable.getColumns().get(4);
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        studentTable.setItems(studentList);
        btnAdd.setOnAction(event -> handleAddStudent());
    }

    @FXML
    private void handleAddStudent() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            LocalDate birthday = LocalDate.parse(txtBirthday.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String gender = radioMale.isSelected() ? "Male" : "Female";
            Student newStudent = new Student(id, name, age, birthday, gender);
            studentList.add(newStudent);
            txtId.clear();
            txtName.clear();
            txtAge.clear();
            txtBirthday.clear();
            radioMale.setSelected(false);
            radioFemale.setSelected(false);
            showAlert("Success", "Student added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers for ID and Age.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    @FXML
    private void handleSort() {
        String selectedOption = comboSort.getSelectionModel().getSelectedItem();

        if (selectedOption != null) {
            switch (selectedOption) {
                case "Name":
                    studentList.setAll(bubbleSortByName());
                    break;
                case "Birthday":
                    studentList.setAll(bubbleSortByBirthday());
                    break;
                case "Age":
                    studentList.setAll(bubbleSortByAge());
                    break;
            }
        }
        studentTable.setItems(studentList);
    }

    private ObservableList<Student> bubbleSortByName() {
        ObservableList<Student> sortedList = FXCollections.observableArrayList(studentList);
        int n = sortedList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedList.get(j).getName().compareToIgnoreCase(sortedList.get(j + 1).getName()) > 0) {
                    Student temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    private ObservableList<Student> bubbleSortByBirthday() {
        ObservableList<Student> sortedList = FXCollections.observableArrayList(studentList);
        int n = sortedList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedList.get(j).getBirthday().isAfter(sortedList.get(j + 1).getBirthday())) {
                    Student temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }

    private ObservableList<Student> bubbleSortByAge() {
        ObservableList<Student> sortedList = FXCollections.observableArrayList(studentList);
        int n = sortedList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedList.get(j).getAge() > sortedList.get(j + 1).getAge()) {
                    Student temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setMinWidth(200);
        alertStage.setMinHeight(100);
        alertStage.setWidth(200);
        alertStage.setHeight(100);

        alert.showAndWait();
    }

}
