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
        String validationResult = cS.customerValidate(name, id);
        if (!"Validation successful".equals(validationResult)) {
            throw new IllegalArgumentException(validationResult);
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
