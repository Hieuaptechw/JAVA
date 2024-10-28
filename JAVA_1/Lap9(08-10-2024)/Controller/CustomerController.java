package Controller;

import Entity.Account;
import Entity.Customer;
import Service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService cs;
    List<Customer> customers;
    public CustomerController(CustomerService cs) {
        this.cs = cs;
    }
    public void updateCustomer(Customer customer) {
        cs.update(customer);
        System.out.println("Khách hàng đã được cập nhật thành công:");
        System.out.println(customer);
    }
    public List<Customer> sortByName() {
        List<Customer> customers = cs.sort();
        System.out.println("Danh sách khách hàng sắp xếp theo tên:");
        customers.forEach(customer -> System.out.println(customer));
        return customers;
    }
    public List<Customer> getCustomerValid() {
       customers = cs.getCustomerValid();
        if (customers.size()>0) {
            System.out.println("Danh sách khách hàng hợp lệ:");
            customers.forEach(customer -> System.out.println(customer));
        } else {
            System.out.println("Không có khách hàng hợp lệ nào.");
        }
        return customers;
    }
    public List<Customer> getCustomerNotValid() {
        customers = cs.getCustomerNotValid();
        if (customers.size()>0) {
            System.out.println("Danh sách khách hàng không hợp lệ:");
            customers.forEach(customer -> System.out.println(customer));
        } else {
            System.out.println("Không có khách hàng không hợp lệ nào.");
        }
        return customers;
    }


}
