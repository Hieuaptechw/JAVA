package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import IGeneric.IGeneral;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoomService implements IGeneral<RoomType> {
    private List<Room> rooms;
    private List<Booking> bookings;
    private List<Customer> customers;

    public RoomService(List<Room> rooms, List<Booking> bookings, List<Customer> customers) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.customers = customers;
    }


    @Override
    public boolean bookRoom(RoomType roomType) {
        return false;
    }

    @Override
    public List<RoomType> displayBooking(String name, String phone, String roomName) {
        return List.of();
    }


    @Override
    public Map<RoomType, Double> calculateRoomType() {
        return Map.of();
    }

    @Override
    public Map<RoomType, Double> roomRevenueMax() {
        Map<RoomType, Double> revenueByRoomType = bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> rooms.get(booking.getRoom_id()).getRoomType(),
                        Collectors.summingDouble(booking -> {
                            Room room = rooms.get(booking.getRoom_id());
                            double hoursRented = booking.calculateRentalHours();
                            return hoursRented * room.getPricePerHour();
                        })
                ));
        Map.Entry<RoomType, Double> maxRevenueEntry = revenueByRoomType.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        Map<RoomType, Double> maxRevenueRoomType = new HashMap<>();
        maxRevenueRoomType.put(maxRevenueEntry.getKey(), maxRevenueEntry.getValue());
        return maxRevenueRoomType;

    }
}
