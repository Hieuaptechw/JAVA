package Entity;

import Services.CustomerService;

public class Customer {
    private int id;
    private String name;
    public Customer() {;}
    private CustomerService cS;
    public Customer(int id, String name, CustomerService cS) {
        this.id = id;
        this.name = name;
        this.cS = cS;
        if (!cS.customerValidate(name, id)) {
            throw new IllegalArgumentException("Invalid customer details.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cS=" + cS +
                '}';
    }
}
