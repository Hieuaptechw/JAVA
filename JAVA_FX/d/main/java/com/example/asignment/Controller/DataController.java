package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataController {
    private static final ObservableList<Budget> budgetList = FXCollections.observableArrayList();

    public static ObservableList<Budget> getBudgetList() {
        return budgetList;
    }

    public static void addBudget(Budget budget) {
        budgetList.add(budget);
    }
}
