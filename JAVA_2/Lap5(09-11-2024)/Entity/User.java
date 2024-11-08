package Entity;

public class User {
    private int id;
    private String username;
    private double balance;

    public User(int id, String username, double balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void placeBet(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Bet placed successfully! Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient funds to place the bet.");
        }
    }

    public void winBet(double amount) {
        balance += amount;
        System.out.println("Congratulations! You've won the bet. Current balance: " + balance);
    }

}
