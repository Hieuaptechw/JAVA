package Service;

import Entity.Customer;
import Entity.Order;
import Entity.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private List<Order> orders;
    private String fileIn;
    private String fileOut;

    public OrderService(String fileIn, String fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    public List<Order> readFile() {
        List<Order> orders = new ArrayList<>();
        try{
            BufferedReader reader   = new BufferedReader(new FileReader(fileIn));
            String lineData;
            while ((lineData = reader.readLine()) != null){
                Order order = new Order();
                if(!lineData.isEmpty()){
                    String[] parts = lineData.split(";");
                    order.setId(Integer.parseInt(parts[0]));
                    order.setCustomerId(Integer.parseInt(parts[1]));
                    order.setDateTime(LocalDateTime.parse(parts[2]));
                }
                orders.add(order);

            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return orders;
    }
    public void writeFile(List<Order> orderList) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            orderList.stream()
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

}
