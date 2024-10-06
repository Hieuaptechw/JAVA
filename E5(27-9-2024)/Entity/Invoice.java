package Entity;

import java.time.LocalDate;

public class Invoice {
    private int id;
    private double amount;
    private Customer customer;
    private LocalDate datetime;

    public Invoice(int id, Customer customer, double amount, LocalDate datetime) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public int getCustomerId() {
        return customer.getId();
    }

    public double getAmountAfterDiscount() {
        return amount * (1 - customer.getDiscount() / 100.0);
    }

    public boolean checkMonth(int month) {
        return (datetime.getMonthValue() == month);
    }

    @Override
    public String toString() {
        return String.format("| %-11d | %-10s | %-8d | %-8.2f | %-10s |",
                id, customer.getName(), customer.getDiscount(), getAmountAfterDiscount(), datetime);
    }
}
