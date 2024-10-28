package Entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int cus_id;
    private String code;
    private LocalDateTime datetime;
    public Order() {};

    public Order(int id, int cus_id, String code, LocalDateTime datetime) {
        this.id = id;
        this.cus_id = cus_id;
        this.code = code;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cus_id=" + cus_id +
                ", code='" + code + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}