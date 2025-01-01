package com.example.asignment.Controller;

import com.example.asignment.Entity.Budget;
import com.example.asignment.Entity.FinancialGoal;
import com.example.asignment.Entity.TransactionType; // Import TransactionType
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HomeController {
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private TableView<FinancialGoal> tableGoal;

    private ObservableList<FinancialGoal> goalList = FXCollections.observableArrayList();
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private PieChart expensePieChart;
    @FXML
    private PieChart incomePieChart;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label totalIncomeLabel;

    @FXML
    private Label totalExpensesLabel;

    @FXML
    private Label balanceLabel;
    @FXML
    public void initialize() {
        TableColumn<FinancialGoal, String > colNamw = (TableColumn<FinancialGoal, String>) tableGoal.getColumns().get(0);
        colNamw.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<FinancialGoal, Double> colAmount = (TableColumn<FinancialGoal, Double>) tableGoal.getColumns().get(1);
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colAmount.setCellFactory(column -> new TableCell<FinancialGoal, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Format the amount to 2 decimal places
                    setText(String.format("%.2f", item));
                }
            }
        });
        updateStatistics();
        loadChartData();
        loadGoals();
        tableGoal.setItems(goalList);
    }
    ObservableList<Budget> expenseList = importExpensesFromFile();
    ObservableList<Budget> incomeList = importIncomeFromFile();
    public void loadChartData() {
        System.out.println("Loading chart data...");
        try {


            Map<String, Double> monthlyExpenseTotalMap = calculateMonthlyTotals(expenseList);
            Map<String, Double> monthlyIncomeTotalMap = calculateMonthlyTotals(incomeList);
            Map<String, Double> expenseCategoryTotals = calculateCategoryTotals(expenseList);
            Map<String, Double> incomeCategoryTotals = calculateCategoryTotals(incomeList);

            updateBarChart(monthlyExpenseTotalMap, monthlyIncomeTotalMap);
            updateExpensePieChart(expenseCategoryTotals);
            updateIncomePieChart(incomeCategoryTotals);
        } catch (Exception e) {
            System.err.println("Error in loadChartData: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void loadGoals() {
        if(goalList.size()<3){
            double exchangeRate = 23000.0;
            goalList.add(new FinancialGoal(1,"Buy a car", 20000000.0 / exchangeRate));
            goalList.add(new FinancialGoal(2,"Travel", 15000000.0 / exchangeRate));
            goalList.add(new FinancialGoal(3,"Buy a house", 50000000.0 / exchangeRate));
        }



    }
    private Map<String, Double> calculateMonthlyTotals(ObservableList<Budget> budgetList) {
        Map<String, Double> monthlyTotalMap = new HashMap<>();
        for (Budget budget : budgetList) {
            LocalDate date = budget.getDate();
            String monthYear = date.getMonth() + " " + date.getYear();
            double amount = budget.getAmount();
            monthlyTotalMap.put(monthYear, monthlyTotalMap.getOrDefault(monthYear, 0.0) + amount);
        }
        return monthlyTotalMap;
    }


    private Map<String, Double> calculateCategoryTotals(ObservableList<Budget> budgetList) {
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Budget budget : budgetList) {
            String category = budget.getCategory();
            double amount = budget.getAmount();
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
        }
        return categoryTotals;
    }

    private void updateExpensePieChart(Map<String, Double> expenses) {
        System.out.println("Updating expense pie chart...");
        Platform.runLater(() -> {
            expensePieChart.getData().clear();
            double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();

            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                double percentage = (entry.getValue() / totalExpenses) * 100;
                PieChart.Data slice = new PieChart.Data(entry.getKey() + " (" + String.format("%.1f", percentage) + "%)", entry.getValue());
                expensePieChart.getData().add(slice);
            }
            updateLegend(expensePieChart);
        });
    }

    private void updateIncomePieChart(Map<String, Double> incomes) {
        System.out.println("Updating income pie chart...");
        Platform.runLater(() -> {
            incomePieChart.getData().clear();
            double totalIncomes = incomes.values().stream().mapToDouble(Double::doubleValue).sum();

            for (Map.Entry<String, Double> entry : incomes.entrySet()) {
                double percentage = (entry.getValue() / totalIncomes) * 100;
                PieChart.Data slice = new PieChart.Data(entry.getKey() + " (" + String.format("%.1f", percentage) + "%)", entry.getValue());
                incomePieChart.getData().add(slice);
            }
            updateLegend(incomePieChart);
        });
    }

    private void updateLegend(PieChart pieChart) {
        VBox legend = new VBox();
        for (Data data : pieChart.getData()) {
            Label label = new Label(data.getName());
            label.setTextFill(data.getNode().getStyle().isEmpty() ? Color.BLACK : Color.valueOf(data.getNode().getStyle().substring(7)));
            legend.getChildren().add(label);
        }

    }

    private void updateBarChart(Map<String, Double> expenses, Map<String, Double> incomes) {
        System.out.println("Updating bar chart...");
        Platform.runLater(() -> {
            barChart.getData().clear();

            XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
            expenseSeries.setName("Expenses");
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                expenseSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
            incomeSeries.setName("Incomes");
            for (Map.Entry<String, Double> entry : incomes.entrySet()) {
                incomeSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            barChart.getData().addAll(expenseSeries, incomeSeries);
        });
    }

    public ObservableList<Budget> importExpensesFromFile() {
        return importBudgetFromFile("expense.txt");
    }

    public ObservableList<Budget> importIncomeFromFile() {
        return importBudgetFromFile("income.txt");
    }

    private ObservableList<Budget> importBudgetFromFile(String fileName) {
        ObservableList<Budget> budgetList = FXCollections.observableArrayList();
        String sysPath = System.getProperty("user.dir");
        String dataFile = sysPath.replace("/", "\\") + "\\src\\main\\java\\com\\example\\asignment\\Database\\" + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3]);
                    TransactionType transactionType = TransactionType.valueOf(parts[4].toUpperCase());
                    String description = parts[5];

                    Budget budget = new Budget(id, category, amount, description, date, transactionType);
                    budgetList.add(budget);
                }
            }
            System.out.println("Budget data imported from " + dataFile);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error parsing line: " + e.getMessage());
        }

        return budgetList;
    }
    private void updateStatistics(){
        double totalIncome = calculateTotalIncome();
        double totalExpenses = calculateTotalExpense();
        double balance = totalIncome - totalExpenses;
        System.out.println("Total income: " + totalIncome);
        System.out.println("Total expenses: " + totalExpenses);
        totalIncomeLabel.setText(String.format("%.2f", totalIncome));
        totalExpensesLabel.setText(String.format("%.2f", totalExpenses));
        balanceLabel.setText(String.format("%.2f", balance));
    }
    private double calculateTotalIncome() {
        double totalIncome = 0.0;
        for (Budget budget  : incomeList) {
            if (budget.getTransactionType() == TransactionType.INCOME) {
                totalIncome += budget.getAmount();
            }
        }
        return totalIncome;
    }
    private double calculateTotalExpense() {
        double totalIncome = 0.0;
        for (Budget budget  : expenseList) {
            if (budget.getTransactionType() == TransactionType.EXPENSE) {
                totalIncome += budget.getAmount();
            }
        }
        return totalIncome;
    }

    public void refresh() {
        initialize();
    }
}
