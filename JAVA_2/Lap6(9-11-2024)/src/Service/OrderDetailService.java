package Service;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderDetailService {
    private List<OrderDetail> orderDetails;
    private List<Product> products;
    private List<Customer> customers;
    private List<Order> orders;
    private String fileIn;
    private String fileOut;

    public OrderDetailService(List<Product> products, List<Customer> customers, List<Order> orders, String fileIn, String fileOut) {
        this.products = products;
        this.customers = customers;
        this.orders = orders;
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    public List<OrderDetail> readFile() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try{
            BufferedReader reader   = new BufferedReader(new FileReader(fileIn));
            String lineData;
            while ((lineData = reader.readLine()) != null){
                OrderDetail orderDetail = new OrderDetail();
                if(!lineData.isEmpty()){
                    String[] parts = lineData.split(";");
                    orderDetail.setId(Integer.parseInt(String.valueOf(parts[0])));
                    orderDetail.setOrderId(Integer.parseInt(parts[1]));
                    orderDetail.setProductId(Integer.parseInt(parts[2]));
                    orderDetail.setQuantity(Integer.parseInt(parts[3]));
                    orderDetail.setPrice(Double.parseDouble(parts[4]));
                }
                orderDetails.add(orderDetail);

            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return orderDetails;
    }
    public void writeFile(List<OrderDetail> orderDetailList) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            orderDetailList.stream()
                    .peek(p->{
                        try{
                            String lineWriter = p.toString(";");
                            bw.write(lineWriter);
                            bw.newLine();
                            bw.flush();

                        }catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                    }).collect(Collectors.toSet());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void displayBilling(String name,List<OrderDetail> orderDetailsList) {
        customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(c -> {
                    System.out.println("Customer Details: ");
                    System.out.println(c.toString("-"));
                    int cusId = c.getId();
                    System.out.println("\nOrders for this customer: ");
                    orders.stream()
                            .filter(o -> o.getCustomerId() == cusId)
                            .forEach(o -> {
                                orderDetailsList.stream()
                                        .filter(od -> od.getOrderId() == o.getId())
                                        .forEach(od -> {
                                            Optional<Product> productOptional = products.stream()
                                                    .filter(p -> p.getId() == od.getProductId())
                                                    .findFirst();
                                            productOptional.ifPresent(product -> {
                                                System.out.println("Product Name: " + product.getName());
                                            });
                                            System.out.println("Quantity: " + od.getQuantity());
                                            System.out.println("Price: " + od.getPrice());
                                        });

                                System.out.println("Date: " + o.getDateTime());
                            });
                });
    }

}
