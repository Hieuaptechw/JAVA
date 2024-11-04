package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Global.Global;
import IGeneric.IGeneral;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService implements IGeneral<Booking> {
    private List< Room> rooms;
    private List< Booking> bookings;
    private List< Customer> customers;

    public BookingService(List< Room> rooms, List<Booking> bookings, List< Customer> customers) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.customers = customers;
    }

    @Override
    public void bookRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên khách hàng: ");
        String customerName = scanner.nextLine();
        System.out.print("Nhập số điện thoại khách hàng: ");
        String customerPhone = scanner.nextLine();

        Optional<Customer> optionalCustomer = customers.stream()
                .filter(c -> Global.ignoreCase(customerName, c.getCus_name()) && c.getCus_phone().equals(customerPhone))
                .findFirst();

        if (!optionalCustomer.isPresent()) {
            System.out.println("Khách hàng không tồn tại.");
            return;
        }

        Customer customer = optionalCustomer.get();

        System.out.print("Nhập tên phòng: ");
        String roomName = scanner.nextLine();
        Optional<Room> optionalRoom = rooms.stream()
                .filter(r -> r.getRoomName().equalsIgnoreCase(roomName))
                .findFirst();

        if (!optionalRoom.isPresent()) {
            System.out.println("Phòng không tồn tại.");
            return;
        }

        Room room = optionalRoom.get();
        int roomId = room.getId();

        System.out.print("Nhập thời gian check-in (yyyy-MM-dd HH:mm:ss): ");
        String checkInInput = scanner.nextLine();
        LocalDateTime checkInDateTime = LocalDateTime.parse(checkInInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.print("Nhập thời gian check-out (yyyy-MM-dd HH:mm:ss): ");
        String checkOutInput = scanner.nextLine();
        LocalDateTime checkOutDateTime = LocalDateTime.parse(checkOutInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        int bookingId = bookings.size() + 1;
        Booking newBooking = new Booking(bookingId, roomId, customer.getId(), checkInDateTime, checkOutDateTime);
        bookings.add(newBooking);

        System.out.println("Phòng đã được đặt thành công! ID đặt phòng: " + newBooking.getId());
    }


    @Override
    public void displayBooking() {
        bookings.forEach(booking -> System.out.println(booking));
    }


    @Override
    public Map<Booking, Double> calculateRoomType() {
        Map<Booking, Double> revenueByBooking = new HashMap<>();
        bookings.forEach(booking -> {
            Room room = rooms.get(booking.getRoom_id());

            if (room != null) {
                double hoursRented = booking.calculateRentalHours();
                double revenue = hoursRented * room.getPricePerHour();

                revenueByBooking.put(booking, revenue);
            } else {
                System.out.println("Warning: Room ID " + booking.getRoom_id() + " not found ");
            }
        });


        return revenueByBooking;
    }

    @Override
    public Map<Booking, Double> roomRevenueMax() {
        return Map.of();
    }


}
