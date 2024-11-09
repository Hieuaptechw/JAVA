package Entity;

public class Customer {
    private int id;
    private String customerCode;
    private String name;

    public Customer() {
    }

    public Customer(int id, String customerCode, String name) {
        this.id = id;
        this.customerCode = customerCode;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(String separator) {
       StringBuilder sb = new StringBuilder();
       return sb.append(this.getId())
               .append(separator)
               .append(this.getCustomerCode())
               .append(separator)
               .append(this.getName())
               .toString();
    }
}
