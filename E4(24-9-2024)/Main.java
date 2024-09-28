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

        maxAmount.stream().forEach(invoice -> {
            Customer customer = invoice.getCustomer();
            double afterDiscount = invoice.getAmount() * (1 - customer.getDiscount() / 100.0);
            System.out.printf("Info Customer Amount MAX =>| %d | %s | Amount: %.2f$\n",
                    customer.getId(),
                    customer.getName(),
                    afterDiscount);
        });

        Optional<Customer> minDiscountCustomer = customers.stream()
                .min(Comparator.comparing(Customer::getDiscount));
        minDiscountCustomer.stream().forEach(customer -> {
            System.out.printf("Info Customer Discount MIN =>| %d | %s | Discount:  %d%%\n",
                    customer.getId(),
                    customer.getName(),
                    customer.getDiscount());
        });
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Invoice ID or Customer Name to search: ");
        String keysearch = scanner.nextLine();

        try {
            int invoiceId = Integer.parseInt(keysearch);
            if (invoiceId > 0) {
                List<Invoice> foundCustomerID = invoices.stream()
                        .filter(invoice -> invoice.getId() == invoiceId)
                        .toList();
                foundCustomerID.forEach(customer -> System.out.println(customer));
            } else {
                System.out.println("InvoiceId not < 0");
            }
        } catch (NumberFormatException e) {
            List<Invoice> foundCustomerName = invoices.stream()
                    .filter(invoice -> invoice.getCustomer().getName().toLowerCase().contains(keysearch.toLowerCase()))
                    .toList();
            foundCustomerName.forEach(customer -> System.out.println(customer));
        }


    }
}