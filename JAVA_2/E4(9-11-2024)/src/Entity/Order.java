package Entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerId;
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(int id, int customerId, LocalDateTime dateTime) {
        this.id = id;
        this.customerId = customerId;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String toString(String separator) {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.getId())
                .append(separator)
                .append(this.getCustomerId())
                .append(separator)
                .append(this.getDateTime())
                .toString();
    }

}
