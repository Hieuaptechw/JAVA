package IGeneric;

import java.util.List;
import java.util.Map;

public interface IGeneral<T> {
    boolean bookRoom(T t);
    List <T>displayBooking(String name, String phone, String roomName);
    Map<T, Double> calculateRoomType();
    Map<T, Double> roomRevenueMax();
}
