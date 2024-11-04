import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;
import Service.RoomService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

       List<Room> rooms = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        rooms.add(new Room(1, RoomType.SINGLE, "Single Room", 8.0));
        rooms.add(new Room(2, RoomType.DOUBLE, "Double Room", 12.0));
        rooms.add(new Room(3, RoomType.QUEEN, "Queen Room", 35.0));
        rooms.add(new Room(4, RoomType.QUEEN, "Triple Room", 12.5));
        rooms.add(new Room(5, RoomType.QUAD, "Quad Room", 20.5));

        customers.add( new Customer(1, "Mr.Linus Tovaldo", "84125325346457"));
        customers.add( new Customer(2, "Mr.Bill", "91124235346467"));
        customers.add( new Customer(2, "Mr.Turing", "911423534646"));



        bookings.add(new Booking(1, 1, 1, LocalDateTime.parse("2023-03-15 09:30:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse("2023-03-16 12:30:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bookings.add(new Booking(2, 1, 2, LocalDateTime.parse("2023-06-09 19:30:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse("2023-06-10 11:25:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bookings.add(new Booking(3, 2, 2, LocalDateTime.parse("2023-03-11 10:10:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse("2023-03-13 11:05:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bookings.add(new Booking(4, 2, 3, LocalDateTime.parse("2023-11-11 11:11:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse("2023-11-13 11:15:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bookings.add(new Booking(5, 2, 3, LocalDateTime.parse("2023-10-25 09:20:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse("2023-10-26 12:25:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bookings.add( new Booking(6, 3, 1, LocalDateTime.parse("2023-08-18 18:25:35", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse("2023-08-19 11:35:20", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));


        BookingService bS = new BookingService(rooms, bookings, customers);
        bS.bookRoom();
        bS.displayBooking();
        Map<Booking, Double> revenueByBooking = bS.calculateRoomType();
        revenueByBooking.forEach((booking, revenue) -> {
            System.out.println("Booking ID: " + booking.getId() + ", Revenue: " + revenue);
        });
        RoomService rS = new RoomService(rooms, bookings, customers);
        Map<RoomType, Double> maxRevenueRoomType = rS.roomRevenueMax();
        maxRevenueRoomType.forEach((roomType, revenue) ->
                System.out.println("Room Type: " + roomType + ", Maximum Revenue: " + revenue)
        );


    }
}
