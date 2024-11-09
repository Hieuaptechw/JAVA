package Entity;

public class OrderDetail {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int productId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String toString(String separator) {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.getId())
                .append(separator)
                .append(this.getOrderId())
                .append(separator)
                .append(this.getProductId())
                .append(separator)
                .append(this.getQuantity())
                .append(separator)
                .append(this.getPrice())
                .toString();
    }
}
