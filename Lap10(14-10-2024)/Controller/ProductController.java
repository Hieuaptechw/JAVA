package Controller;

import Entity.Product;
import ExceptionValidation.InvalidProductCodeException;
import ExceptionValidation.InvalidProductNameException;
import ExceptionValidation.InvalidQuantityException;
import Service.ProductService;
import Validation.Validation;

public class ProductController {
    private ProductService ps;
    public ProductController(){;};
    public ProductController(ProductService ps) {
        this.ps = ps;
    }
    public void addProduct(Product product){
        if(Validation.isProductCode(product.getCode()) && Validation.isProductName(product.getName()) && Validation.isNumberInterger(String.valueOf(product.getQuantity()))){
            ps.add(product);
        }
        if(!Validation.isProductCode(product.getCode()))
        {
            throw new InvalidProductCodeException("Product Code Not Format");
        }
        if(!Validation.isProductName(product.getName()))
        {
            throw new InvalidProductNameException("Product Name Not Format");
        }
        if(!Validation.isNumberInterger(String.valueOf(product.getQuantity()))){

            throw new InvalidQuantityException("Product Quantity is Not Interger");
        }
    }
}
