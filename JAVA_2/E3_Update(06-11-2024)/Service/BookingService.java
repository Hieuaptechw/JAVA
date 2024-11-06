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
    public List<Room> getRooms(RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        List<Room> filteredRooms = rooms.stream()
                .filter(r -> r.getRoomType().equals(roomType))
                .collect(Collectors.toList());
        List<Room> availableRooms = filteredRooms.stream()
                .filter(room -> bookings.stream()
                        .map(booking ->
                                booking.getRoom_id() == room.getId() &&
                                        (booking.getCheck_in_datetime().isBefore(checkOut) && booking.getCheck_out_datetime().isAfter(checkIn))
                        )
                        .noneMatch(isBooked -> isBooked)
                )
                .collect(Collectors.toList());
        return availableRooms;
    }
    public Optional<Customer>  checkUser(String name, String phone) {
        Optional<Customer> customersFilter = customers.stream()
                .filter(c -> Global.ignoreCase(c.getCus_name(), name) && Global.ignoreCase(c.getCus_phone(), phone))
                .findFirst();
        customersFilter.ifPresent(c -> {

            System.out.println("Customer found: " + c.getCus_name() + ", " + c.getCus_phone());
        });


        if (!customersFilter.isPresent()) {
            Customer newCustomer = new Customer(customers.size()+1,name, phone);
            customers.add(newCustomer);
            System.out.println("Customer not found. Added new customer: " + newCustomer.getCus_name());
        }
        return customersFilter;
    }
    public int getIdUser(String name, String phone) {
        Optional<Customer> customer = customers.stream()
                .filter(c -> Global.ignoreCase(c.getCus_name(), name) && Global.ignoreCase(c.getCus_phone(), phone))
                .findFirst();
        if (customer.isPresent()) {
            return customer.get().getId();
        } else {
            System.out.println("Customer not found.");
            return -1;
        }
    }

    public int getIdBooking(){
        return bookings.size()+1;
    };



    @Override
    public boolean bookRoom(Booking booking) {
        if (booking != null) {
            bookings.add(booking);
            return true;
        } else {
            return false;
        }
    }


    public List<Booking> displayBooking(String name, String phone, String roomName) {
        int cus_id = getIdUser(name, phone);
        Optional<Room> room = rooms.stream()
                .filter(r -> Global.ignoreCase(r.getRoomName(), roomName))
                .findFirst();
        if (room.isPresent()) {
            int roomId = room.get().getId();
            return bookings.stream()
                    .filter(booking -> booking.getRoom_id() == roomId && booking.getCus_id() == cus_id)
                    .collect(Collectors.toList());
        } else {
            System.out.println("Room not found with the specified name: " + roomName);
            return List.of();
        }
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
