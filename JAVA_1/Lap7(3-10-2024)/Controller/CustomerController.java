package Controller;

import Entity.Customer;
import Service.CustomerService;

import java.util.List;

public class CustomerController {
    private static List<Customer> customers ;
    private CustomerService cs;
    public CustomerController(List<Customer> customers) {
        this.customers = customers;
    }
    public Customer getById(int id){
        Customer customer = cs.getById(id);
        if(customer != null){
            return customer;
        }
        return null;
    }
}
