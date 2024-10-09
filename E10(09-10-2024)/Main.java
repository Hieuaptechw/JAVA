import Controller.OrderDetailController;
import Entity.*;
import Services.CustomerService;
import Services.OrderService;
import Controller.OrderController;
import Services.ProductService;
import Services.OrderDetailService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        ProductService pS = new ProductService(products);
        CustomerService cS = new CustomerService(customers);
        OrderDetailService oDS = new OrderDetailService(orders, products, customers, orderDetailsList);
        OrderService oS = new OrderService(orders, products, customers, orderDetailsList);

        products.add(new Product("MS123456", "Product Hiu", 10, pS));
        products.add(new Product("MS123457", "Product Hiu", 5, pS));

        customers.add(new Customer(1, "Hieu", cS));
        customers.add(new Customer(2, "Mai", cS));
        Order order = new Order("ORDPM12345678", 1, LocalDate.now(), oS);
        OrderDetails orderDetail = new OrderDetails(1, order.getId(), products.get(0).getId(), 1, Status.CANCELLED, oDS);
        orderDetailsList.add(orderDetail);

        OrderController oC = new OrderController(oS, oDS, orders, products, customers, orderDetailsList);
        OrderDetailController oDC = new OrderDetailController(oDS, orders, customers, products, orderDetailsList);

        try {
            oC.insertOrder(order);
            oDC.updateOrderDetailStatus(1,Status.COMPLETED);
            oDC.displayAllOrderDetails();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
