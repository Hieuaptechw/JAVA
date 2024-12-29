package com.example.asignment.Controller;

public class ControllerManager {
    private static ControllerManager instance;
    private CategoryController categoryController;
    private ExpenseController expenseController;

    private ControllerManager() {
        categoryController = new CategoryController();
        expenseController = new ExpenseController();
    }

    public static ControllerManager getInstance() {
        if (instance == null) {
            instance = new ControllerManager();
        }
        return instance;
    }

    public CategoryController getCategoryController() {
        return categoryController;
    }

    public ExpenseController getExpenseController() {
        return expenseController;
    }
}
