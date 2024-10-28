package Service;

import Entity.Customer;
import IGeneral.IGeneric;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService implements IGeneric<Customer> {
    private List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    @Override
    public Customer getById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Customer> getByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> existingCustomer = customers.stream()
                .filter(c -> c.getId() == customer.getId())
                .findFirst();

        if (existingCustomer.isPresent()) {
            customers.remove(existingCustomer.get());
            customers.add(customer);
            return customer;
        }

        return null;
    }

    @Override
    public void delete(Customer customer) {
        customers.removeIf(c -> c.getId() == customer.getId());
    }
}
