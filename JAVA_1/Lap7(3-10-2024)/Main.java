import Controller.CustomerController;
import Entity.Customer;
import Service.CustomerService;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        CustomerService cs = new CustomerService(customers);
        CustomerController cc = new CustomerController(customers,cs);
        customers.add(new Customer(1,"123","C2309"));
        customers.add(new Customer(2,"123","C2309"));
        customers.add(new Customer(3,"123","C2309"));

        Customer cus = cc.getById(1);
        if(cus!= null){
           System.out.println(cus);
        }else{
            System.out.println("Not found");
        }


    }
}