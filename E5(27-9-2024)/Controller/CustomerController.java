package Controller;

import Entity.Account;
import Entity.Customer;
import Entity.Gender;
import Entity.Invoice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class CustomerController {
    private static List<Customer> customers;
    private static List<Invoice> invoices;
    private static List<Account> accounts;


    public CustomerController(List<Customer> customers,List<Account> accounts,List<Invoice> invoices) {
        CustomerController.customers = customers;
        CustomerController.accounts = accounts;
        CustomerController.invoices = invoices;

    }

    public void displayCustomers() {
        System.out.println("----------------------------------------------------");
        System.out.printf("| %-11s | %-10s | %-10s | %-8s |\n", "Id Customer", "Name", "Gender", "Discount");
        for (Customer customer : customers) {
            System.out.println("----------------------------------------------------");
            System.out.printf("| %-11d | %-10s | %-10s | %-8d |\n",
                    customer.getId(),
                    customer.getName(),
                    customer.getGender(),
                    customer.getDiscount());
        }
    }

    public void sortCustomersByName() {
        customers = customers.stream()
                .sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());
        System.out.println("List Customers Sort By Name");
        displayCustomers();
    }
    public double getBalance(int customerId) {
        for (Account account : accounts) {
            if (account.getCustomerId() == customerId) {
                return account.getBalance();
            }
        }
        return 0;
    }
    public double getAmountAfterDiscount(int customerId) {
        for (Invoice invoice : invoices) {
            if(invoice.getCustomerId() == customerId) {
                return invoice.getAmountAfterDiscount();
            }
        }
        return 0;
    }
    public double getAmount(int customerId) {
        for (Invoice invoice : invoices) {
            if(invoice.getCustomerId() == customerId) {
                return invoice.getAmount();
            }
        }
        return 0;
    }


    public void inforCustomerValid() {
        System.out.println("List of Customers Who Can Make Payment");
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            double balance = getBalance(customer.getId());
            double amount = getAmountAfterDiscount(customer.getId());

            if (balance >= amount) {
                System.out.println("---------------------------------------------------------------------------");
                System.out.printf("|  Name : %-5s | Balance : %-10.2f$ | Amount(After Discount) : %-5.2f$ |\n",
                        customer.getName(), balance, amount);
            }

        }
        System.out.println("---------------------------------------------------------------------------");

    }
    public void inforCustomerNotValid() {
        System.out.println("List of Customers Who Can Not Make Payment");
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            double balance = getBalance(customer.getId());
            double amount = getAmount(customer.getId());

            if (balance < amount) {
                System.out.println("-----------------------------------------------------------");
                System.out.printf("|  Name : %-5s | Balance : %-10.2f$ | Amount : %-5.2f$ |\n",
                        customer.getName(), balance, amount);
            }

        }
        System.out.println("-----------------------------------------------------------");

    }


}
