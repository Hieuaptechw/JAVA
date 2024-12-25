package com.example.asignment.Entity;

import java.time.LocalDate;

public class Budget {
    private int id;
    private Category category;
    private Source source;
    private String name;
    private double amount;
    private String description;
    private LocalDate date;
    private TransactionType transactionType;

    public Budget() {
    }

    public Budget(Source source, int id, String name, double amount, String description, LocalDate date, TransactionType transactionType) {
        this.source = source;
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.transactionType = transactionType;
    }

    public Budget(int id, Category category, String name, double amount, String description, LocalDate date, TransactionType transactionType) {
        this.id = id;
        this.category = category;
        this.name = name;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", category=" + category +
                ", source=" + source +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", transactionType=" + transactionType +
                '}';
    }
}
