package Entity;

public abstract class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public abstract void deposit(double amount);

    public abstract void  withdraw(double amount);

    @Override
    public String toString() {
        return String.format("| %-11s | %-10.2f |",
                accountNumber, balance);
    }

}