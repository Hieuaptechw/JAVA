package Controller;

import Entity.*;
import Services.OrderDetailService;

import java.util.List;

public class OrderDetailController {
    private OrderDetailService orderDetailService;
    private List<Order> orders;
    private List<Customer> customers;
    private List<Product> products;
    private List<OrderDetails> orderDetails;

    public OrderDetailController(OrderDetailService orderDetailService, List<Order> orders, List<Customer> customers, List<Product> products, List<OrderDetails> orderDetails) {
        this.orderDetailService = orderDetailService;
        this.orders = orders;
        this.customers = customers;
        this.products = products;
        this.orderDetails = orderDetails;
    }

    public void updateOrderDetailStatus(int id, Status status) {
        orderDetailService.updateStatus(orderDetails, id, status);
        displayAllOrderDetails();
    }


    public void displayAllOrderDetails() {
        if (orderDetails.isEmpty()) {
            System.out.println("No order details found.");
        } else {
            orderDetails.forEach(orderDetail -> System.out.println(orderDetail.toString()));
        }
    }

}
