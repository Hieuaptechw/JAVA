package Services;

import Entity.*;
import Exceptions.InvalidQuantityException;
import Exceptions.NotEnoughInventoryException;
import Global.OrderDetailValidator;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailService {
    private List<Order> orders;
    private List<Product> products;
    private List<Customer> customers;
    private List<OrderDetails> orderDetails;
    private OrderDetailValidator oDV;

    public OrderDetailService(List<Order> orders, List<Product> products, List<Customer> customers, List<OrderDetails> orderDetails) {
        this.oDV = new OrderDetailValidator();
        this.orders = new ArrayList<>();
        this.products = products;
        this.customers = customers;
        this.orderDetails = new ArrayList<>();
    }

    public boolean orderDetailValidate(int quantity) {
        if (!oDV.quantityValidate(quantity)) {
            throw new InvalidQuantityException("Quantity must be a positive integer");
        }
        return true;
    }

    public boolean isStockAvailable(String productId, int quantityNeeded) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .map(product -> product.getQuantity() >= quantityNeeded)
                .orElse(false);
    }

    public void insertOrderDetails(Order order, List<OrderDetails> orderDetailsToAdd) {
        long insufficientStockCount = orderDetailsToAdd.stream()
                .filter(od -> !isStockAvailable(od.getProduct_id(), od.getQuantity()))
                .count();

        if (insufficientStockCount > 0) {
            throw new NotEnoughInventoryException("Not enough inventory for some products.");
        }

        orders.add(order);
        this.orderDetails.addAll(orderDetailsToAdd);

        orderDetailsToAdd.forEach(od -> {
            products.stream()
                    .filter(product -> product.getId().equals(od.getProduct_id()))
                    .findFirst()
                    .ifPresent(product -> product.setQuantity(product.getQuantity() - od.getQuantity()));
        });
    }
    public void updateStatus(List<OrderDetails> orderDetailsList, int id, Status status) {
        orderDetailsList.stream()
                .filter(od -> od.getId() == id)
                .forEach(od -> {
                    od.setStatus(status);
                });
    }


}
