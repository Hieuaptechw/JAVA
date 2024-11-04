package Entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class Booking {
    private int id;
    private int room_id;
    private int cus_id;
    private LocalDateTime check_in_datetime;
    private LocalDateTime check_out_datetime;

    public Booking() {
    }

    public Booking(int id, int room_id, int cus_id, LocalDateTime check_in_datetime, LocalDateTime check_out_datetime) {
        this.id = id;
        this.room_id = room_id;
        this.cus_id = cus_id;
        this.check_in_datetime = check_in_datetime;
        this.check_out_datetime = check_out_datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public LocalDateTime getCheck_in_datetime() {
        return check_in_datetime;
    }

    public void setCheck_in_datetime(LocalDateTime check_in_datetime) {
        this.check_in_datetime = check_in_datetime;
    }

    public LocalDateTime getCheck_out_datetime() {
        return check_out_datetime;
    }

    public void setCheck_out_datetime(LocalDateTime check_out_datetime) {
        this.check_out_datetime = check_out_datetime;
    }
    public long calculateRentalHours() {
        return Duration.between(check_in_datetime, check_out_datetime).toHours();
    }
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", cus_id=" + cus_id +
                ", check_in_datetime=" + check_in_datetime +
                ", check_out_datetime=" + check_out_datetime +
                ", rental_hours=" + calculateRentalHours()+
                '}';
    }

}
