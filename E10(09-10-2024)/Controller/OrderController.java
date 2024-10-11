package Controller;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetails;
import Entity.Product;
import Services.OrderDetailService;
import Services.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private List<Order> orders;
    private List<Customer> customers;
    private List<Product> products;
    private List<OrderDetails> orderDetails;

    public OrderController(OrderService orderService, OrderDetailService orderDetailService, List<Order> orders, List<Product> products, List<Customer> customers, List<OrderDetails> orderDetails) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.orders = orders;
        this.customers = customers;
        this.products = products;
        this.orderDetails = orderDetails;
    }

    public void insertOrder(Order order) {
        orderDetailService.insertOrderDetails(order, orderDetails);
        orders.forEach(o -> System.out.println(o.toString()));
        orderDetails.forEach(orderDetail -> System.out.println(orderDetail.toString()));
    }

}
