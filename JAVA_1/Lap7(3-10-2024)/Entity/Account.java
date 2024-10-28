package Entity;

import java.time.LocalDateTime;

public class Account {
    private int id;
    private String accountNumber;
    private Customer customer;
    private double balance;
    private LocalDateTime createdAccount;

    public Account(){
        ;
    }

    public Account(int id, String accountNumber, Customer customer, double balance, LocalDateTime createdAccount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.createdAccount = createdAccount;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(LocalDateTime createdAccount) {
        this.createdAccount = createdAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", customer=" + customer +
                ", balance=" + balance +
                ", createdAccount=" + createdAccount +
                '}';
    }
}
