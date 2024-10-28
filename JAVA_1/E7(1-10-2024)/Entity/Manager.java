package Entity;

public class Manager extends Staff {
    private double allowance;

    public Manager(String name, double salary, double allowance) {
        super(name, salary);
        this.allowance = allowance;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    @Override
    public double getPaid() {
        return getSalary() + allowance;
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
        return "Manager";
    }

}
