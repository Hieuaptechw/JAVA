package Entity;

import java.time.LocalDate;

public class Invoice extends Customer {
    private int id;
    private double amount;
    private Customer customer;
    private LocalDate datetime;

    public Invoice(int id,String name, Gender gender, int discount, double amount, LocalDate datetime) {
        super(id,name,gender, discount);
        this.id = id;
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

    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public Gender getGender(){
        return super.getGender();
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
        return getId();
    }

    public double getAmountAfterDiscount() {
        return amount * (1 - getDiscount() / 100.0);
    }
    public boolean checkMonth(int month) {
        return (datetime.getMonthValue()==month);
    }
    @Override
    public String toString() {
        return String.format("| %-11d | %-10s | %-8d | %-8.2f | %-10s |",
                id, getName(), getDiscount(), getAmountAfterDiscount(), datetime);
    }
}
