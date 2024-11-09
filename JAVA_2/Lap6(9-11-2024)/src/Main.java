import Entity.Customer;
import Entity.Product;
import Service.ProductService;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<Customer>();
        String sysPath = System.getProperty("user.dir");
        String fileIn = sysPath.replace("/","\\")   +"/database/customer.in.txt";
        String fileOut = sysPath.replace("/","\\")   +"/database/customer.out.txt";
        String productFileIn = sysPath.replace("/","\\")   +"/database/product.in.txt";
        String productFileOut = sysPath.replace("/","\\")   +"/database/product.out.txt";
        ProductService pS = new ProductService(productFileOut, productFileIn);
        List<Product> products = pS.readFile();
        products.forEach(p-> System.out.println(p.toString()));
        products.forEach(System.out::println);
//        try{
//            BufferedReader reader   = new BufferedReader(new FileReader(fileIn));
//        String lineData;
//        while ((lineData = reader.readLine()) != null){
//            Customer customer = new Customer();
//            if(!lineData.isEmpty()){
//                String[] parts = lineData.split(";");
//                customer.setId(Integer.parseInt(String.valueOf(parts[0])));
//                customer.setCustomerCode(String.valueOf(parts[1]));
//                customer.setName(String.valueOf(parts[2]));
//            }
//            customers.add(customer);
//
//        }
//        }catch(IOException e){
//            System.out.println(e.getMessage());
//        }
//        Set<Customer> customerSet = customers.stream()
//                .sorted(Comparator.comparing(Customer::getName))
//                .collect(Collectors.toSet());
////        customerSet.forEach(c-> System.out.println(c.toString()));
//        try{
//            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
//            customerSet.stream()
//                    .peek(c->{
//                        try{
//                            String lineWriter = c.toString(";");
//                            bw.write(lineWriter);
//                            bw.newLine();
//                            bw.flush();
//                        }catch(IOException e){
//                            System.out.println(e.getMessage());
//                        }
//
//                    }).collect(Collectors.toSet());
//        }catch(IOException e){
//            System.out.println(e.getMessage());
//        }


    }
}