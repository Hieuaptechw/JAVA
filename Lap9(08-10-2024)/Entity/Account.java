package Entity;

public class Account {
    private int id;
    private double balance;
    private Customer customer;

    public Account() {
    }

    public Account(int id, Customer customer, double balance) {
        this.id = id;
        this.customer = customer;
        this.balance = balance;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customer.getName();
    }
    public int getCustomerId() {
        return customer.getId();
    }

    public Account deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited " + amount);
        } else {
            System.out.println("Cannot deposit ");
        }
        return this;
    }
    public Account withdraw(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
            System.out.println("Withdrawn " + amount);
        } else {
            System.out.println("Cannot withdraw; exceeds the current balance");
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("| %-11d | %-10s | %-10s | %-8d | %-8.2f |",
                id, getCustomerName(), customer.getGender(), customer.getDiscount(), balance);
    }
}
