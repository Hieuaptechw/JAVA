package Entity;

import Services.OrderDetailService;

import java.util.Locale;

public class OrderDetails {
    private int id;
    private String order_id;
    private String product_id;
    private int quantity;
    private Status status;
    private OrderDetailService oDS;

    public OrderDetails() {}
    public OrderDetails(int id, String order_id, String product_id, int quantity, Status status, OrderDetailService oDS) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.status = status;
        this.oDS = oDS;

        if (!oDS.orderDetailValidate(quantity)) {
            throw new IllegalArgumentException("Invalid product details.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("| %-10d | %-10s | %-10s | %-8d | %-12s | %-10s |",
                id, order_id, product_id, quantity, status, oDS);
    }


}
