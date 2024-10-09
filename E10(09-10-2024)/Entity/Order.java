package Entity;

import Services.OrderService;

import java.time.LocalDate;

public class Order {
    private String id;
    private int cus_id;
    private LocalDate datetime;
    private OrderService oS;
    public Order() {};

    public Order(String id, int cus_id, LocalDate datetime, OrderService oS) {

        this.id = id;
        this.cus_id = cus_id;
        this.datetime = datetime;
        if (!oS.orderValidate(id)) {
            throw new IllegalArgumentException("Invalid Order.");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-10d | %-20s | %-20s |",
                id, cus_id, datetime, oS);
    }


}
