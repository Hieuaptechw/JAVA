package Services;

import Entity.Product;
import Exceptions.InvalidProducIdException;
import Exceptions.InvalidProductNameException;
import Exceptions.InvalidQuantityException;
import Global.ProductValidator;

import java.util.List;

public class ProductService {
    private List<Product> products;
    private ProductValidator pV;

    public ProductService(List<Product> products) {
        this.products = products;
        this.pV = new ProductValidator();
    }

    public String productValidate(String id, String name, int quantity) {
        try {
            if (!pV.idValidate(id)) {
                throw new InvalidProducIdException("Invalid product ID format");
            }
            if (!pV.nameValidate(name)) {
                throw new InvalidProductNameException("Invalid product name format");
            }
            if (!pV.quantityValidate(quantity)) {
                throw new InvalidQuantityException("Quantity must be a positive integer");
            }
            return "Validation successful";
        } catch (InvalidProducIdException | InvalidProductNameException | InvalidQuantityException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }



    public boolean findProductById(String id) {
        try {
            return products.stream()
                    .filter(product -> product.getId().equals(id))
                    .findFirst()
                    .isPresent();
        } catch (Exception e) {
            return false;
        }
    }

}
