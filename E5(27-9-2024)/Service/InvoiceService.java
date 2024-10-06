package Service;

import Entity.Gender;
import Entity.Invoice;
import Global.Global;
import IGeneric.IGeneral;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceService implements IGeneral<Invoice> {
    private List<Invoice> invoices;

    public InvoiceService(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    public List<Invoice> sortByName() {
        return invoices.stream()
                .sorted(Comparator.comparing(i -> i.getCustomer().getName()))
                .toList();
    }
    public Invoice getById(int id) {
        return invoices.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Invoice> getByName(String keyword) {
        return invoices.stream()
                .filter(i ->Global.ignoreCase(i.getCustomer().getName(), keyword))
                .toList();
    }
    public List<Invoice> saleFemale() {
        List<Invoice> femaleInvoices = invoices.stream()
                .filter(i -> i.getCustomer().getGender() == Gender.FEMALE)
                .filter(i -> i.getDatetime().getMonthValue() == 8)
                .collect(Collectors.toList());
        femaleInvoices.forEach(invoice -> {
            int discountMore = 10;
            int discountCustomer = invoice.getCustomer().getDiscount();
            double amountAfterDiscount = invoice.getAmount() * (1 - (discountCustomer + discountMore) / 100.0);
            System.out.println("------------------------------------------------------------------------");
            System.out.printf("| %-11d | %-10s | %7d%% | %7d%% | %-7.2f | %-10s |\n",
                    invoice.getId(),
                    invoice.getCustomer().getName(),
                    discountCustomer,
                    discountCustomer + discountMore,
                    amountAfterDiscount,
                    invoice.getDatetime());
        });
        return femaleInvoices;
    }

}
