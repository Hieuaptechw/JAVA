package Controller;

import Entity.Account;
import Entity.Customer;
import Entity.Gender;
import Entity.Invoice;
import Service.AccountService;
import Service.InvoiceService;

import java.util.Comparator;
import java.util.List;



public class InvoiceController {
    private InvoiceService is;
    private List<Invoice> invoices;

    public InvoiceController(InvoiceService is) {
        this.is = is;
    }
    public void updateInvoice(Invoice invoice) {
        is.update(invoice);
        System.out.println("Hóa đơn đã được cập nhật thành công:");
        System.out.println(invoice);
    }
    public List<Invoice> sortByName() {
        invoices = is.sort();
        System.out.println("Danh sách hoa đơn sắp xếp theo tên:");
        invoices.forEach(invoice -> System.out.println(invoice));
        return invoices;
    }

    public Invoice getInvoiceById(int id) {
        Invoice  invoice = is.getById(id);
        if (invoice != null) {
            System.out.println("Thông tin hoá đơn với ID " + id + ": " + invoice);
        } else {
            System.out.println("Không tìm thấy hoá đơn với ID: " + id);
        }
        return invoice;
    }

    public List<Invoice> getInvoicesByName(String keyword) {
       invoices =is.getByName(keyword);
        if (invoices.size()>0) {
            System.out.println("Thông tin hoá đơn với tên :" + keyword);
            invoices.forEach(invoice -> System.out.println(invoice));
        } else {
            System.out.println("Không tìm hoa đơn với tên: " + keyword);
        }
        return invoices;
    }
    public List<Invoice> saleFemale() {
        List<Invoice> femaleInvoices = is.saleFemale();
        if (femaleInvoices.size()>0) {
            System.out.println("Danh sách hoá đơn trong tháng 8 mua bởi nữ:");
            femaleInvoices.forEach(invoice -> System.out.println(invoice));
        } else {
            System.out.println("Không có hóa đơn nào trong tháng 8 cho khách hàng nữ.");
        }
        return femaleInvoices;
    }

}
