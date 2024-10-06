import Controller.AccountController;
import Controller.CustomerController;
import Controller.InvoiceController;
import Entity.Account;
import Entity.Customer;
import Entity.Gender;
import Entity.Invoice;
import Service.AccountService;
import Service.CustomerService;
import Service.InvoiceService;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Invoice> invoices = new ArrayList<>();

        customers.add(new Customer(1, "Hieu", Gender.FEMALE, 14));
        customers.add(new Customer(2, "Mai", Gender.FEMALE, 20));
        customers.add(new Customer(3, "Hai", Gender.FEMALE, 35));
        accounts.add(new Account(1, customers.get(0), 3700));
        accounts.add(new Account(2, customers.get(1), 4700));
        accounts.add(new Account(3, customers.get(2), 200));
        invoices.add(new Invoice(1, customers.get(0), 300, LocalDate.of(2005, 9, 24)));
        invoices.add(new Invoice(2, customers.get(1), 600, LocalDate.of(2005, 8, 24)));
        invoices.add(new Invoice(3, customers.get(2), 900, LocalDate.of(2005, 8, 24)));
        CustomerService cs = new CustomerService(customers, invoices, accounts);
        AccountService as = new AccountService(accounts);
        InvoiceService is = new InvoiceService(invoices);
        CustomerController cc = new CustomerController(cs);
        AccountController ac = new AccountController(as);
        InvoiceController ic = new InvoiceController(is);
        cc.sortByName();
        ac.sortByName();
        ic.sortByName();

        ac.getAccountById(5);
        ac.getAccountByName("Hieu");
        ic.getInvoiceById(4);
        ic.getInvoicesByName("Hieu");

        cc.getCustomerValid();
        cc.getCustomerNotValid();


        ic.saleFemale();

    }
}
