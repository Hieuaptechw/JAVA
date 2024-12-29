package com.example.asignment.Controller;

import com.example.asignment.Entity.Category;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryController {

    @FXML
    private MenuButton categoryMenuButton;
    public void setMenuButton(MenuButton menuButton) {
        this.categoryMenuButton = menuButton;
    }
    @FXML
    public void initialize() {
        loadCategoriesIntoMenu();
    }

    private void loadCategoriesIntoMenu() {
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath.replace("/","\\")   +"\\src\\main\\java\\com\\example\\asignment\\Database\\category.txt";
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
}
