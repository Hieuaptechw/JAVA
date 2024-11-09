package Service;

import Entity.Customer;
import Entity.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products ;
    private String fileOut;
    private String fileIn;

    public ProductService( String fileOut,String fileIn) {
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
}
