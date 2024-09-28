package Controller;

import Entity.Account;
import Entity.Customer;

import java.util.Comparator;
import java.util.List;


public class AccountController {
    private static List<Account> accounts;

    public AccountController(List<Account> accounts) {
        AccountController.accounts = accounts;
    }

    public void displayAccounts() {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-11s | %-10s | %-10s | %-8s | %-8s |\n", "Id Account", "Name", "Gender", "Discount", "Balance");
        for (Account account : accounts) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("| %-11d | %-10s | %-10s | %-8d | %-8.2f |\n",
                    account.getId(),
                    account.getName(),
                    account.getGender(),
                    account.getDiscount(),
                    account.getBalance());

        }
    }
    public void sortAccountsByName() {
        accounts = accounts.stream()
                .sorted(Comparator.comparing(Customer::getName))
                .toList();
        System.out.println("List Account Sort By Name");
        displayAccounts();
    }
    public void searchAccountByNameID(String id, String name) {
        int idSearch = Integer.parseInt(id);
        List<Account> accountSearchResults = accounts.stream()
                .filter(a -> a.getId() == idSearch && a.getName().equalsIgnoreCase(name))
                .toList();

        accountSearchResults.forEach(account ->
                System.out.printf("Infor Account:\nID Account: %d\nName Account: %s\nGender Account: %s\nDiscount Account: %d%%\nBalance Account: %.2f\n",
                        account.getId(),
                        account.getName(),
                        account.getGender(),
                        account.getDiscount(),
                        account.getBalance())
        );

    }


}
