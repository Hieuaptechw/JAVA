package Entity;

public  class CurentAccount extends BankAccount {

    public CurentAccount(String accountNumber,double balance){
        super(accountNumber,balance);
    }
    @Override
    public double getBalance(){
        return super.getBalance();
    }
    @Override
    public void setBalance(double balance){
        super.setBalance(balance);
    }
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.setBalance(getBalance() + amount);
            System.out.println("Deposited " + amount);
        } else {
            System.out.println("Cannot deposit " + amount);
        }
    }
    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            this.setBalance(getBalance() - amount);
            System.out.println("Withdrawn " + amount );
        } else {
            System.out.println("Cannot withdraw; exceeds the current balance of ");
        }

    }
}
