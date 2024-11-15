import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;
import Service.CustomerService;
import Service.OrderDetailService;
import Service.OrderService;
import Service.ProductService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String sysPath = System.getProperty("user.dir");
        String customerFileIn = sysPath.replace("/","\\")   +"/database/customer.in.txt";
        String customerFileOut = sysPath.replace("/","\\")   +"/database/customer.out.txt";
        String productFileIn = sysPath.replace("/","\\")   +"/database/product.in.txt";
        String productFileOut = sysPath.replace("/","\\")   +"/database/product.out.txt";
        String orderFileIn = sysPath.replace("/","\\")   +"/database/order.in.txt";
        String orderFileOut = sysPath.replace("/","\\")   +"/database/order.out.txt";
        String orderdetailFileIn = sysPath.replace("/","\\")   +"/database/orderdetails.in.txt";
        String orderdetailFileOut = sysPath.replace("/","\\")   +"/database/orderdetails.out.txt";
        ProductService pS = new ProductService(productFileIn,productFileOut);
        CustomerService cS = new CustomerService(customerFileIn, customerFileOut);
        OrderService oS = new OrderService(orderFileIn, orderFileOut);
        List<Product> products = pS.readFile();
        List<Customer> customers = cS.readFile();
        List<Order> orders = oS.readFile();
        OrderDetailService oDS = new OrderDetailService(products,customers,orders,orderdetailFileIn, orderdetailFileOut);
        List<OrderDetail> orderDetails = oDS.readFile();
        products.forEach(p-> System.out.println(p.toString(";")));
        customers.forEach(c-> System.out.println(c.toString(";")));
        orders.forEach(o-> System.out.println(o.toString(";")));
        orderDetails.forEach(o-> System.out.println(o.toString(";")));
        pS.writeFile(products);
        cS.writeFile(customers);
        oS.writeFile(orders);
        oDS.writeFile(orderDetails);
        oDS.displayBilling("Jane Smith",orderDetails);


    }
}