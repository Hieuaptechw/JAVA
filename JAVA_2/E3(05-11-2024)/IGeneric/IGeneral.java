package IGeneric;

import java.util.Map;

public interface IGeneral<T> {
    void bookRoom();
    void displayBooking();
    Map<T, Double> calculateRoomType();
    Map<T, Double> roomRevenueMax();
}
