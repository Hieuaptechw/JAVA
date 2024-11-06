package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookingController {
    private List<Room> rooms;
    private List<Booking> bookings;
    private List<Customer> customers;
    private BookingService bSC;

    public BookingController(BookingService bookingService) {
        this.bSC = bookingService;
    }

    public void bookingRoom() {
        RoomType selectedRoomType;
        System.out.println("Booking Room");
        System.out.println("Available room types:");

        for (RoomType type : RoomType.values()) {
            System.out.println("- " + type);
        }
        System.out.print("Choose room type: ");
        Scanner sc = new Scanner(System.in);
        selectedRoomType = RoomType.valueOf(sc.next().toUpperCase());
        sc.nextLine();

        System.out.print("Enter date check-in (yyyy-MM-dd HH:mm:ss): ");
        String checkInInput = sc.nextLine();
        LocalDateTime checkInDateTime = LocalDateTime.parse(checkInInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.print("Enter date check-out (yyyy-MM-dd HH:mm:ss): ");
        String checkOutInput = sc.nextLine();
        LocalDateTime checkOutDateTime = LocalDateTime.parse(checkOutInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<Room> filteredRooms = bSC.getRooms(selectedRoomType, checkInDateTime, checkOutDateTime);

        if (filteredRooms.isEmpty()) {
            System.out.println("No rooms available for the selected type.");
            return;
        } else {
            System.out.println("Available rooms of type " + selectedRoomType + ":");
            for (int i = 0; i < filteredRooms.size(); i++) {
                System.out.println(i+ "." + filteredRooms.get(i));
            }
            System.out.print("Enter the room number to book: ");
            int roomNumber = sc.nextInt();
            sc.nextLine();
            if (roomNumber < 1 || roomNumber > filteredRooms.size()) {
                System.out.println("Invalid room number.");
                return;
            }

            Room selectedRoom = filteredRooms.get(roomNumber - 1);

            System.out.print("Enter customer name: ");
            String name = sc.nextLine();
            System.out.print("Enter customer phone: ");
            String phone = sc.nextLine();

            Optional<Customer> customer = bSC.checkUser(name, phone);
            if (!customer.isPresent()) {
                System.out.println("Customer not found. Adding new customer...");
            }
            int booking_id = bSC.getIdBooking();
            int customer_id = bSC.getIdUser(name,phone);
            Booking booking = new Booking(booking_id, selectedRoom.getId(),customer_id, checkInDateTime, checkOutDateTime);
            if(bSC.bookRoom(booking)){
                System.out.println("Booking successful for customer: " + customer.get().getCus_name() + " in room: " + selectedRoom.getRoomName());
            }else {
                System.out.println("Failed to book the room.");
            }


        }
    }

    public void displayBooking() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name: ");
        String name = sc.nextLine();
        System.out.print("Enter the phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter the room name: ");
        String roomName = sc.nextLine();

        List<Booking> bookings = bSC.displayBooking(name, phone, roomName);

        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                System.out.println("Booking Details:");
                System.out.println("Customer Name: " + name);
                System.out.println("Customer Phone: " + phone);
                System.out.println("Room Name: " + roomName);
                System.out.println("Check-in Date: " + booking.getCheck_in_datetime());
                System.out.println("Check-out Date: " + booking.getCheck_out_datetime());
            }
        } else {
            System.out.println("No booking found for the specified customer and room.");
        }
    }


}
