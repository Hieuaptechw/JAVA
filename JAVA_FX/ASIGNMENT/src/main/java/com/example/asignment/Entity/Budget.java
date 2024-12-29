package com.example.asignment.Entity;

import java.time.LocalDate;

public class Budget {
    private int id;
    private String category;
    private double amount;
    private String description;
    private LocalDate date;
    private TransactionType transactionType;

    public Budget() {
    }

    public Budget(int id, String category, double amount, String description, LocalDate date, TransactionType transactionType) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.transactionType = transactionType;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", transactionType=" + transactionType +
                '}';
    }
}
