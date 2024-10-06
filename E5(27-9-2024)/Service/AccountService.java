package Service;

import Entity.Account;
import Entity.Invoice;
import Global.Global;
import IGeneric.IGeneral;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AccountService implements IGeneral<Account> {
    private List<Account> accounts;

    public AccountService(List<Account> accounts) {
        this.accounts = accounts;
    }
    public List<Account> sortByName() {
        return accounts.stream()
                .sorted(Comparator.comparing(a -> a.getCustomer().getName()))
                .toList();
    }
    public Account getById(int id) {
        return accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Account> getByName(String keyword) {
        return accounts.stream()
                .filter(a -> Global.ignoreCase(a.getCustomer().getName(), keyword))
                .toList();
    }
}
