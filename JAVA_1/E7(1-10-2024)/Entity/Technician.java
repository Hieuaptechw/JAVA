package Entity;

public class Technician extends Staff {
    private double overtimePay;

    public Technician(String name, double salary, double overtimePay) {
        super(name, salary);
        this.overtimePay = overtimePay;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }


    @Override
    public double getPaid() {
        return getSalary() + overtimePay;
    }


    @Override
    public String toString() {
        System.out.println("---------------------------------------------------------------------------------------------");
        return String.format("| %-20s | %-20s | %-20s | %-20s |",
                getRole(),
                getName(),
                String.format("%.2f", getSalary()),
                String.format("%.2f", getPaid()));
    }

    public String getRole() {
        return "Technician";
    }
}
