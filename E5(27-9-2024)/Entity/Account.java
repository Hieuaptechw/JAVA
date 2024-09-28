package Entity;

public class Account extends Customer {
    private int id;
    private double balance;
    private Customer customer;
    public Account() {
        super();
    }


    public Account(int id, String name, Gender gender, int discount, double balance) {
        super(id, name, gender, discount);
        this.id = id;
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

    public String getCustomerName() {
        return getName();
    }
    public int getCustomerId() {
        return getId();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited " + amount + " to " + getCustomerName());
        } else {
            System.out.println("Cannot deposit " + amount + " to " + getCustomerName());
        }
        return this;
    }

    public Account withdraw(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
            System.out.println("Withdrawn " + amount + " from " + getCustomerName());
        } else {
            System.out.println("Cannot withdraw; exceeds the current balance of " + getCustomerName());
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("| %-11d | %-10s | %-10s | %-8d | %-8.2f |",
                id, getCustomerName(), getGender(), getDiscount(), balance);
    }
}
