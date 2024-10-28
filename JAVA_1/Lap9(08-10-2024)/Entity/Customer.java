package Entity;
import Entity.Account;
public class Customer {
    private int id;
    private String name;
    private Gender gender;
    private int discount;
    public Customer(int id, String name, Gender gender, int discount) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.discount = discount;

    }

    public Customer() {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {

        return String.format("| %-11d | %-10s | %-10s | %-8d |", id, name, gender,discount);
    }
}
