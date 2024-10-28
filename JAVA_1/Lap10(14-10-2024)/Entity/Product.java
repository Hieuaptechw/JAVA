package Entity;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private String code;
    public Product() {;}



    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Product(int id, String name, int quantity, String code) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product update(Product product) {
        this.setName(product.getName());
        this.setQuantity(product.getQuantity());
        this.setCode(product.getCode());
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", code='" + code + '\'' +
                '}';
    }
}