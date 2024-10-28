package Service;

import Entity.Account;
import Global.Global;
import IGeneric.IGeneral;

import java.util.Comparator;
import java.util.List;

public class AccountService implements IGeneral<Account> {
    private List<Account> accounts;

    public AccountService(List<Account> accounts) {
        this.accounts = accounts;
    }
    @Override
    public void update(Account account) {
        accounts.stream()
                .filter(a -> a.getId() == account.getId())
                .forEach(a -> {
                    a.getCustomer().setName(account.getCustomer().getName());
                    a.getCustomer().setDiscount(account.getCustomer().getDiscount());
                    a.setBalance(account.getBalance());
                });
    }

    @Override
    public List<Account> sort() {
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
