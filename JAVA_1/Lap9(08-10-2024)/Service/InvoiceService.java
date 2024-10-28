package Service;

import Entity.Gender;
import Entity.Invoice;
import Global.Global;
import IGeneric.IGeneral;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceService implements IGeneral<Invoice> {
    private List<Invoice> invoices;

    public InvoiceService(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public void update(Invoice invoice) {
        invoices.stream()
                .filter(i -> i.getId() == invoice.getId())
                .forEach(i -> {
                    i.getCustomer().setName(invoice.getCustomer().getName());
                    i.getCustomer().setDiscount(invoice.getCustomer().getDiscount());
                    i.setAmount(invoice.getAmount());
                    i.setDatetime(invoice.getDatetime());
                });
    }

    public List<Invoice> sort() {
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
        int discountMore = 10;
        return invoices.stream()
                .filter(i -> i.getCustomer().getGender() == Gender.FEMALE)
                .filter(i -> i.getDatetime().getMonthValue() == 8)
                .map(i -> {
                    int discountCustomer = i.getCustomer().getDiscount();
                    double amountAfterDiscount = i.getAmount() * (1 - (discountCustomer + discountMore) / 100.0);
                    return new Invoice(i.getId(), i.getCustomer(), amountAfterDiscount, i.getDatetime());
                })
                .toList();
    }


}
