package Entity;

public enum Status {
    PENDING("Pending"),
    COMPLETED("Completed"),
    PAID("Paid"),
    CANCELLED("Cancell");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}