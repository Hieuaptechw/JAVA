import Entity.SavingAccount;
import Entity.CurentAccount;
public class Main {
    public static void main(String[] args) {
        double ibal,damt,warnt;
        ibal = 1000.00;
        SavingAccount savingAccount = new SavingAccount("SA001",1200.12);
        System.out.println("Savings A/c: Initial Balance $"+ ibal);
        damt =500.00;
        savingAccount.deposit(damt);
        warnt = 1600.00;
        System.out.println("Try to withdraw :$"+warnt);
        savingAccount.withdraw(warnt);
        System.out.println("");
        ibal = 5000.00;
        CurentAccount currencyAccount = new CurentAccount("CA001",1000.00);
        System.out.println("Current A/c: Initial Balance $"+ ibal);
        damt = 2500.00;
        currencyAccount.deposit(1000.00);
        warnt = 2500.00;
        currencyAccount.withdraw(3000.0);
        warnt = 6000.00;
        System.out.println("Try to withdraw: $"+warnt);
        savingAccount.withdraw(warnt);

    }
}