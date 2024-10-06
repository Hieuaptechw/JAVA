package Service;

import Entity.Account;
import Entity.Customer;
import Entity.Invoice;
import IGeneric.ICustomer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService implements ICustomer<Customer> {
    private List<Customer> customers;
    private List<Invoice> invoices;
    private List<Account> accounts;

    public CustomerService(List<Customer> customers, List<Invoice> invoices, List<Account> accounts) {
        this.customers = customers;
        this.invoices = invoices;
        this.accounts = accounts;
    }
    public List<Customer> sortByName() {
        return customers.stream()
                .sorted(Comparator.comparing(c -> c.getName()))
                .toList();
    }

    public double getAmountAfterDiscount(int customerId) {
        double amountAfterDiscount = invoices.stream()
                .filter(i -> i.getCustomer().getId() == customerId)
                .map(Invoice::getAmountAfterDiscount)
                .findFirst()
                .orElse(0.0);
        return amountAfterDiscount;
    }
    public double getAmount(int customerId) {
        double amount =  invoices.stream()
                .filter(i -> i.getCustomerId() == customerId)
                .map(Invoice::getAmount)
                .findFirst()
                .orElse(0.0);
        return amount;
    }
    public double getBalance(int customerId) {
        double balance = accounts.stream()
                .filter(account -> account.getCustomerId() == customerId)
                .map(Account::getBalance)
                .findFirst()
                .orElse(0.0);
        return balance;
    }

    public List<Customer> getCustomerValid() {
        return customers.stream()
                .filter(customer ->getBalance(customer.getId()) >= getAmountAfterDiscount(customer.getId()))
                .toList();
    }
    public List<Customer> getCustomerNotValid() {
        return customers.stream()
                .filter(customer ->getBalance(customer.getId()) <  getAmount(customer.getId()))
                .toList();
    }
}
