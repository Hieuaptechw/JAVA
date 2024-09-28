import Controller.AccountController;
import Controller.CustomerController;
import Controller.InvoiceController;
import Entity.Account;
import Entity.Customer;
import Entity.Gender;
import Entity.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //a
        List<Account> accounts = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Invoice> invoices = new ArrayList<>();

        customers.add(new Customer(1, "Hieu", Gender.FEMALE, 14));
        customers.add(new Customer(2, "Mai", Gender.FEMALE, 20));
        customers.add(new Customer(3, "Hai", Gender.FEMALE, 35));

        accounts.add(new Account(1, customers.get(0).getName(), customers.get(0).getGender(), customers.get(0).getDiscount(), 1000.00));
        accounts.add(new Account(2, customers.get(1).getName(), customers.get(1).getGender(), customers.get(1).getDiscount(), 1500.00));
        accounts.add(new Account(3, customers.get(2).getName(), customers.get(2).getGender(), customers.get(2).getDiscount(), 10.00));

        invoices.add(new Invoice(1, customers.get(0).getName(), customers.get(0).getGender(), customers.get(0).getDiscount(), 40.45, LocalDate.of(2005, 8, 24)));
        invoices.add(new Invoice(2, customers.get(1).getName(), customers.get(1).getGender(), customers.get(1).getDiscount(), 23.45, LocalDate.of(2024, 9, 13)));
        invoices.add(new Invoice(3, customers.get(2).getName(), customers.get(2).getGender(), customers.get(2).getDiscount(), 85.45, LocalDate.of(1996, 8, 11)));


        CustomerController ct = new CustomerController(customers,accounts,invoices);
        AccountController ac = new AccountController(accounts);
        InvoiceController iv = new InvoiceController(invoices);
        System.out.println("---------------------------------------------------------------");
        ct.sortCustomersByName();
        System.out.println("---------------------------------------------------------------");
        ac.sortAccountsByName();
        System.out.println("---------------------------------------------------------------");
        iv.sortInvoicesByName();
        //b
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();
        System.out.println("---------------------------------------------------------------");
        System.out.print("Enter Account Name: ");
        String name = scanner.nextLine();
        System.out.println("---------------------------------------------------------------");
        ac.searchAccountByNameID(id, name);
        System.out.println("---------------------------------------------------------------");
        //c
        ct.inforCustomerValid();
        ct.inforCustomerNotValid();
        //d
        iv.invoicesInAugust();
    }
}
