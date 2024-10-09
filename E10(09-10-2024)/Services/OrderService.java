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
    public OrderService(List<Order> order,List<Product> product,List<Customer> customer,List<OrderDetails> orderDetails) {
        this.orders = new ArrayList<>();
        this.orderDetails = new ArrayList<>();
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.oV = new OrderValidator();
    }
    public boolean orderValidate(String id) {
        if(!oV.idValidate(id)){
            throw new InvalidOrderIdException("Invalid Order ID Format");
        }
        return true;
    };
    public boolean isStockAvailable(String productId, int quantityNeeded) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .map(product -> product.getQuantity() >= quantityNeeded)
                .orElse(false);
    }
    public void insertDetails(Order order) {
        orders.add(order);
    }
}
