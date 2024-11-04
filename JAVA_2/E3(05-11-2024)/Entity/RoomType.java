package Entity;

public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double"),
    QUAD("Quad"),
    QUEEN("Queen");
    private String  roomTypeLable;
    RoomType(String roomTypeLable) {
        this.roomTypeLable = roomTypeLable;
    }

    public String getRoomTypeLable() {
        return roomTypeLable;
    }

    public void setRoomTypeLable(String roomTypeLable) {
        this.roomTypeLable = roomTypeLable;
    }
}
