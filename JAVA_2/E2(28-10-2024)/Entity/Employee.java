package Entity;

public class Employee {
    private int id;
    private String name;
    private int deptId;
    private String dob;
    private String gender;
    private String city;
    private String province;
    private String phoneNumber;

    public Employee() {
    }

    public Employee(int id, String name, int deptId, String dob, String gender, String city, String province, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.phoneNumber = phoneNumber;
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

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %-20s | %-7d | %-10s | %-6s | %-15s | %-10s | %-15s |\n",
                id, name, deptId, dob, gender, city, province, phoneNumber);
    }

}
