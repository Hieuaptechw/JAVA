package Service;

import Entity.Product;
import IGeneral.IGeneric;

import java.util.List;

public class ProductService implements IGeneric<Product>{
private List<Product> products;
private ProductService(){;};
private ProductService(List<Product> products ){
        this.products = products;
}
    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getByName(String name) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(Product product) {

    }
}
