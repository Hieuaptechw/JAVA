package com.example.asignment.Entity;

public enum TransactionType {
    INCOME("Income"),
    EXPENSE("Expense");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
