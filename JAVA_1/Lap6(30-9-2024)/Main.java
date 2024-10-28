import Entity.SavingAccount;
import Entity.CurrentAccount;

public class Main {
    public static void main(String[] args) {
        double ibal, damt, warnt;

        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-15s | %-15s | %-10s |\n", "Account Type", "Action", "Amount ($)", "Balance ($)", "Account No");
        System.out.println("---------------------------------------------------------------------------------");
        ibal = 1000.00;
        SavingAccount savingAccount = new SavingAccount("SA001", ibal);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Savings A/c", "Starting", 0.0, savingAccount.getBalance(), savingAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");
        damt = 500.00;
        savingAccount.deposit(damt);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Savings A/c", "Deposit", damt, savingAccount.getBalance(), savingAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");
        warnt = 250.00;
        savingAccount.withdraw(warnt);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Savings A/c", "Withdraw", warnt, savingAccount.getBalance(), savingAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");

        warnt = 1600.00;
        savingAccount.withdraw(warnt);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Savings A/c", "Withdraw", warnt, savingAccount.getBalance(), savingAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");
        ibal = 5000.00;
        CurrentAccount currentAccount = new CurrentAccount("CA001", ibal);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Current A/c", "Starting", 0.0, currentAccount.getBalance(), currentAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");

        damt = 2500.00;
        currentAccount.deposit(damt);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Current A/c", "Deposit", damt, currentAccount.getBalance(), currentAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");
        warnt = 1250.00;
        currentAccount.withdraw(warnt);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Current A/c", "Withdraw", warnt, currentAccount.getBalance(), currentAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");
        warnt = 6000.00;
        savingAccount.withdraw(warnt);
        System.out.printf("| %-15s | %-10s | %-15.2f | %-15.2f | %-10s |\n",
                "Savings A/c", "Withdraw", warnt, savingAccount.getBalance(), savingAccount.getAccountNumber());
        System.out.println("---------------------------------------------------------------------------------");
    }
}
