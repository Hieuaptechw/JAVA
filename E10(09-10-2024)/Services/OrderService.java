package Services;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetails;
import Entity.Product;
import Exceptions.InvalidOrderIdException;
import Exceptions.NotEnoughInventoryException;
import Global.OrderValidator;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;
    private List<Product> products;
    private List<Customer> customers;
    private List<OrderDetails> orderDetails;
    private OrderValidator oV;

    public OrderService(List<Order> order, List<Product> product, List<Customer> customer, List<OrderDetails> orderDetails) {
        this.orders = new ArrayList<>(order);
        this.orderDetails = new ArrayList<>(orderDetails);
        this.products = new ArrayList<>(product);
        this.customers = new ArrayList<>(customer);
        this.oV = new OrderValidator();
    }

    public String orderValidate(String id) {
        try {
            if (!oV.idValidate(id)) {
                throw new InvalidOrderIdException("Invalid Order ID Format");
            }
            return "Validation successful";
        } catch (InvalidOrderIdException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public boolean isStockAvailable(String productId, int quantityNeeded) {
        try {
            return products.stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst()
                    .map(product -> product.getQuantity() >= quantityNeeded)
                    .orElse(false);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertDetails(Order order) {
        try {
            orders.add(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
