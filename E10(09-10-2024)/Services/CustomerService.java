package Services;

import Entity.Customer;
import Exceptions.InvalidCustomerIdException;
import Exceptions.InvalidCustomerNameException;
import Global.CustomerValidator;

import java.util.List;

public class CustomerService {
    private List<Customer> customers;
    private CustomerValidator cV;
    public CustomerService(List<Customer> customers) {
        this.customers = customers;
        this.cV = new CustomerValidator();
    }
    public String customerValidate(String name, int id) {
        try {
            if (!cV.nameValidate(name)) {
                throw new InvalidCustomerNameException("Invalid Customer Name Format");
            }
            if (!cV.idValidate(id)) {
                throw new InvalidCustomerIdException("Invalid Customer ID Format");
            }
            return "Validation successful";
        } catch (InvalidCustomerNameException | InvalidCustomerIdException e) {
            return e.getMessage();
        }
    }


    public boolean findCustomerById(int id) {
        try {
            return customers.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .isPresent();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }






}
