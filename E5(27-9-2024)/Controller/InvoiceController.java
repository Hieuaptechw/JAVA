package Controller;

import Entity.Customer;
import Entity.Gender;
import Entity.Invoice;

import java.util.Comparator;
import java.util.List;



public class InvoiceController {
    private static List<Invoice> invoices;
    public InvoiceController(List<Invoice> invoices) {
        InvoiceController.invoices = invoices;
    }
    public void displayInvoices() {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-11s | %-10s | %-5s | %-8s | %-10s |\n", "Id", "Name", "Discount", "Amount", "Date");

        for (Invoice invoice : invoices) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("| %-11d | %-10s | %-8d | %-8.2f | %-10s |\n",
                    invoice.getId(),
                    invoice.getName(),
                    invoice.getDiscount(),
                    invoice.getAmountAfterDiscount(),
                    invoice.getDatetime());
        }
        System.out.println("---------------------------------------------------------------");
    }
    public void sortInvoicesByName() {
        invoices = invoices.stream()
                .sorted(Comparator.comparing(Customer::getName))
                .toList();
        System.out.println("List Invoices Sort By Name");
        displayInvoices();
    }
    public static void saleFemale() {
        List<Invoice> femaleinvoice = invoices.stream()
                .filter(i -> i.getGender() == Gender.FEMALE)
                .filter(i -> i.getDatetime().getMonthValue() == 8)
                .toList();
        System.out.println("Sale for Female in August:");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-11s | %-10s | %-8s | %-8s | %-7s | %-10s |\n", "Id", "Name", "Discount","(More)", "Amount", "Date");
        femaleinvoice.forEach(invoice -> {
            int DiscountMore = 10;
            int DiscountCustomer = invoice.getDiscount();
            double AmountAfterDiscount = invoice.getAmount() * (1 - (DiscountCustomer + DiscountMore) / 100.0);
                System.out.println("-------------------------------------------------------------------------");
                System.out.printf("| %-11d | %-10s | %7d%% | %7d%% | %-7.2f | %-10s |\n",
                        invoice.getId(),   invoice.getName(),
                        invoice.getDiscount(),
                        DiscountCustomer+DiscountMore,
                        AmountAfterDiscount,
                        invoice.getDatetime());
        });
        System.out.println("-------------------------------------------------------------------------");


    }



}
