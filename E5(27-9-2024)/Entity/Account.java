package Entity;

public class Account {
    private int id;
    private Customer customer;
    private double balance;

    public Account(Customer customer, int id, double balance) {
        this.customer = customer;
        this.id = id;
        this.balance = balance;
    }

    public Account() {
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

    }
    public Account deposit(double amount) {

    }
    public Account withdraw(double amount) {}


}
