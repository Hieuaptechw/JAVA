package Controller;

import Entity.Account;
import Entity.Customer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
        System.out.println("List Account Sort By Name");
        displayAccounts();
    }
    public void searchAccountByNameID(String id, String name) {
        int idSearch = Integer.parseInt(id);
        Optional<Account> accountsearch = accounts.stream()
                .filter(a -> a.getId() == idSearch && a.getName().equalsIgnoreCase(name))
                .findFirst();

        accountsearch.ifPresentOrElse(account ->
                System.out.printf("Infor Account:\nID Account: " + account.getId() +
                        "\nName Account: " + account.getName() +
                        "\nGender Account: " + account.getGender() +
                        "\nDiscount Account: " + account.getDiscount() + "%%" +
                        "\nBalance Account: " + account.getBalance() + "\n"),
        () -> System.out.println("Not Found Account !"));
    }


}
