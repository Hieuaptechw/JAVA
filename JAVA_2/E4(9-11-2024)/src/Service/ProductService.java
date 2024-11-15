package Service;

import Entity.Customer;
import Entity.Product;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private List<Product> products ;
    private String fileOut;
    private String fileIn;

    public ProductService( String fileIn,String fileOut) {
        this.fileOut = fileOut;
        this.fileIn = fileIn;
    }
    public List<Product> readFile() {
        List<Product> productList = new ArrayList<>();
        try{
            BufferedReader reader   = new BufferedReader(new FileReader(fileIn));
            String lineData;
            while ((lineData = reader.readLine()) != null){
                Product products = new Product();
                if(!lineData.isEmpty()){
                    String[] parts = lineData.split(";");
                    products.setId(Integer.parseInt(String.valueOf(parts[0])));
                    products.setName(String.valueOf(parts[1]));
                    products.setPrice(Double.parseDouble(String.valueOf(parts[2])));
                    products.setQuantity(Integer.parseInt(String.valueOf(parts[3])));
                }
                productList.add(products);

            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }
    public void writeFile(List<Product> productList) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            productList.stream()
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
