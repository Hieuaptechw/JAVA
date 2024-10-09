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
    public void updateOrderDetailStatus(int orderDetailId, Status status) {
        System.out.println("OrderDetail Update");
        orderDetailService.updateStatus(orderDetails, orderDetailId, status);
    }

    public void displayAllOrderDetails() {
        orderDetails.forEach(orderDetail -> System.out.println(orderDetail.toString())); // In ra thông tin của từng OrderDetail
    }

}
