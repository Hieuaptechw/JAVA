import Controller.ProductController;
import Entity.Product;
import ExceptionValidation.InvalidProductCodeException;
import ExceptionValidation.InvalidProductNameException;
import ExceptionValidation.InvalidQuantityException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        List <Product> products = new ArrayList<Product>();

        ProductController pc = new ProductController();
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        while(!quit) {
            try {
                System.out.println(products.size());
                Product product = new Product();
                System.out.println("Enter Product Code:");
                String pCode = sc.nextLine();
                product.setId(products.size()+1);
                product.setCode(pCode);
                System.out.println("Enter Product Name:");
                String pName = sc.nextLine();
                product.setName(pName);
                System.out.println("Enter Product Quantity:");
                String pQuantity = sc.nextLine();
                product.setQuantity(parseInt(pQuantity));
                pc.addProduct(product);
            }catch (InvalidProductCodeException e) {
                System.out.println(e.getMessage());
            }catch (InvalidProductNameException e) {
                System.out.println(e.getMessage());
            }catch(InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
            finally {
                System.out.println("Product valid");
            }
        }



    }
}