package Entity;

public class Room {
    private int id;
    private RoomType roomType;
    private String roomName;
    private double pricePerHour;

    public Room() {
    }

    public Room(int id, RoomType roomType, String roomName, double pricePerHour) {
        this.id = id;
        this.roomType = roomType;
        this.roomName = roomName;
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", roomName='" + roomName + '\'' +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}
