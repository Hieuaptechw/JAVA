package Entity;

public class Staff extends Person {
    private String id;
    private String school;
    private double pay;
    private Gender gender;

    public Staff() {
        super();
    }

    public Staff(String id,String school ,String name,Gender gender ,double pay, String address) {
        super(name, address);
        this.id = id;
        this.school = school;
        this.gender = gender;
        this.pay = pay;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("| %-5s | %-10s | %-5s | %-10s | %-5.2f| %-8s |",
                id, getName(), school,gender,pay, super.getAddress());
    }
}
