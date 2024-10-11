package Entity;
import Global.ProductValidator;
import Services.ProductService;

public class Product {
    private String id;
    private String name;
    private int quantity;
    private ProductService pS;
    public Product() {;}

    public Product(String id, String name, int quantity, ProductService pS) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.pS = pS;
        String validationResult = pS.productValidate(id,name,quantity);
        if (!"Validation successful".equals(validationResult)) {
            throw new IllegalArgumentException(validationResult);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", pS=" + pS +
                '}';
    }
}
