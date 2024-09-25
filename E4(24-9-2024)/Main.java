import Entity.Customer;
import Entity.Invoice;

import java.util.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        List<Invoice> invoices = new ArrayList<>();
        customers.add(new Customer(1, "Phi Hieu", 17));
        customers.add(new Customer(2, "Van Hung", 20));
        customers.add(new Customer(2, "Duy Trung", 25));
        invoices.add(new Invoice(1, customers.get(0), 120.50));
        invoices.add(new Invoice(2, customers.get(1), 1230.50));
        invoices.add(new Invoice(3, customers.get(2), 230.50));
        invoices.add(new Invoice(4, customers.get(0), 22.50));
        invoices.add(new Invoice(5, customers.get(1), 56.50));
        invoices.add(new Invoice(6, customers.get(2), 15.0));
        System.out.println("List Customer:");
        System.out.println("| ID   | Name Customer    | Amount    |");
        System.out.println("|------|------------------|-----------|");
        invoices.forEach(System.out::println);

        Optional<Invoice> maxAmount = invoices.stream()
                .max(Comparator.comparing(Invoice::getAmount));

        maxAmount.ifPresent(invoice -> {
            Customer customer = invoice.getCustomer();
            double afterdiscount = invoice.getAmount() * (1 - customer.getDiscount() / 100.0);
            System.out.printf("Info Customer Amount MAX =>| %d | %s | Amount: %.2f$\n",
                    customer.getId(),
                    customer.getName(),
                    afterdiscount);
        });
        Optional<Customer> minDiscountCustomer = customers.stream()
                .min(Comparator.comparing(Customer::getDiscount));

        minDiscountCustomer.ifPresent(customer -> {
            System.out.printf("Info Customer Discount MIN =>| %d | %s | Discount:  %d%%\n",
                    customer.getId(),
                    customer.getName(),
                    customer.getDiscount());
        });
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Invoice ID or Customer Name to search: ");
        String keysearch = scanner.nextLine();

        if (keysearch.matches("\\d+")) {
            int invoiceId = Integer.parseInt(keysearch);
            boolean found = false;
            for (Invoice invoice : invoices) {
                if (invoice.getId() == invoiceId) {
                    System.out.println("Found Invoice: " + invoice);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Invoice ID not found.");
            }
        } else {
            boolean check = false;
            for (Invoice invoice : invoices) {
                if (invoice.getCustomer().getName().equalsIgnoreCase(keysearch)) {
                    System.out.println("Found Invoice: " + invoice);
                    check = true;
                }
            }
            if (!check) {
                System.out.println("Customer Name not found.");
            }
        }


    }
}