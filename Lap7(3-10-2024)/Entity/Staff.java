package Entity;

import java.time.LocalDateTime;

public class Staff {
    private int id;
    private String code;
    private String name;
    private LocalDateTime Dob;


    public Staff(){
        ;
    }

    public Staff(int id, String code, String name, LocalDateTime dob) {
        this.id = id;
        this.code = code;
        this.name = name;
        Dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDob() {
        return Dob;
    }

    public void setDob(LocalDateTime dob) {
        Dob = dob;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", Dob=" + Dob +
                '}';
    }
}
