package Service;

import Entity.Customer;
import Entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    private List<Customer> customers;
    private String fileIn;
    private String fileOut;

    public CustomerService(String fileIn, String fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    public List<Customer> readFile() {
        List<Customer> customers = new ArrayList<Customer>();
        try{
            BufferedReader reader   = new BufferedReader(new FileReader(fileIn));
        String lineData;
        while ((lineData = reader.readLine()) != null){
            Customer customer = new Customer();
            if(!lineData.isEmpty()){
                String[] parts = lineData.split(";");
                customer.setId(Integer.parseInt(String.valueOf(parts[0])));
                customer.setCustomerCode(String.valueOf(parts[1]));
                customer.setName(String.valueOf(parts[2]));
            }
            customers.add(customer);

        }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return customers;
    }
    public void writeFile(List<Customer> customerList) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            customerList.stream()
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
