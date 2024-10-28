package Entity;

import java.time.LocalDate;

public class Student extends Person {
    private String id;
    private LocalDate dob;
    private Gender gender;

    public Student() {
        super();
    }

    public Student(String id, String name, LocalDate dob, Gender gender, String address) {
        super(name, address);
        this.id = id;
        this.gender = gender;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public int getAge(){
        return Math.abs(LocalDate.now().getYear()-this.getDob().getYear());
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String toString() {
        return String.format("| %-5s | %-10s | %-10s | %-5d | %-8s | %s |",
                id, getName(), dob,getAge(), gender, super.getAddress());
    }
}
