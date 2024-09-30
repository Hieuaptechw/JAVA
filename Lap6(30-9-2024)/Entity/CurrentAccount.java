package Entity;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double getBalance() {
        return super.getBalance();
    }

    @Override
    public void setBalance(double balance) {
        super.setBalance(balance);
    }
    @Override
    public String getAccountNumber() {
        return super.getAccountNumber();
    }
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.setBalance(getBalance() + amount);
            System.out.println("==> Deposited of $" + amount + " successful .Current balance: $"+this.getBalance());
            System.out.println("---------------------------------------------------------------------------------");
        } else {
            System.out.println("==> Cannot deposit $ " + amount);
            System.out.println("---------------------------------------------------------------------------------");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            this.setBalance(getBalance() - amount);
            System.out.println("==> Withdraw of $" + amount + " successful .Current balance: $"+this.getBalance());
            System.out.println("---------------------------------------------------------------------------------");
        } else {
            System.out.println("==> Insufficient funds Withdrawal failed");
            System.out.println("---------------------------------------------------------------------------------");
        }
    }
}
