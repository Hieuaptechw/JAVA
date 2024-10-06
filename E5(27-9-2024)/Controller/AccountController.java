package Controller;

import Entity.Account;
import Service.AccountService;

import java.util.List;

public class AccountController {
    private AccountService as;
    private List<Account> accounts;

    public AccountController(AccountService as) {
        this.as = as;
    }

    public List<Account> sortByName() {
        accounts = as.sortByName();
        System.out.println("Danh sách tài khoản sắp xếp theo tên:");
        accounts.forEach(account -> System.out.println(account));
        return accounts;
    }

    public Account getAccountById(int id) {
        Account account = as.getById(id);
        if (account != null) {
            System.out.println("Thông tin tài khoản với ID " + id + ": " + account);
        } else {
            System.out.println("Không tìm thấy tài khoản với ID: " + id);
        }
        return account;
    }

    public List<Account> getAccountByName(String keyword) {
        List<Account> accounts = as.getByName(keyword);
        if (!accounts.isEmpty()) {
            System.out.println("Thông tin tài khoản với tên :" + keyword);
            accounts.forEach(account -> System.out.println(account));
        } else {
            System.out.println("Không tìm thấy tài khoản với tên: " + keyword);
        }
        return accounts;
    }

}
