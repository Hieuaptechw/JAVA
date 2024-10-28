package Services;

import Entity.*;
import Exceptions.InvalidQuantityException;
import Exceptions.NotEnoughInventoryException;
import Global.OrderDetailValidator;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderDetailService {
    private List<Order> orders;
    private List<Product> products;
    private List<Customer> customers;
    private List<OrderDetails> orderDetails;
    private OrderDetailValidator oDV;

    public OrderDetailService(List<Order> orders, List<Product> products, List<Customer> customers, List<OrderDetails> orderDetails) {
        this.oDV = new OrderDetailValidator();
        this.orders = orders;
        this.products = products;
        this.customers = customers;
        this.orderDetails = orderDetails;
    }

    public String orderDetailValidate(int quantity) {
        try {
            if (!oDV.quantityValidate(quantity)) {
                throw new InvalidQuantityException("Quantity must be a positive integer");
            }
            return "Validation successful";
        } catch (InvalidQuantityException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }


    public boolean isStockAvailable(String productId, int quantityNeeded) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .map(product -> product.getQuantity() >= quantityNeeded)
                .orElse(false);
    }


    public void insertOrderDetails(Order order, List<OrderDetails> orderDetailsToAdd) {
        try {
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
        } catch (NotEnoughInventoryException e) {
            throw new NotEnoughInventoryException("Not enough inventory for some products.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }


    public void updateStatus(List<OrderDetails> orderDetailsList, int id, Status status) {
        try {
            OrderDetails orderDetail = orderDetailsList.stream()
                    .filter(od -> od.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("OrderDetail with ID " + id + " not found."));
            orderDetail.setStatus(status);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



}
