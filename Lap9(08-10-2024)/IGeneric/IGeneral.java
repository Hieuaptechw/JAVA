package IGeneric;

import java.util.List;

public interface IGeneral<T> {
    void update(T obj);
    List <T> sort();
    T getById(int id);
    List<T> getByName(String name);
//    List<T> getCustomerVaild();
//    List<T> getCustomerNotVaild();
//    List<T> getCustomerDiscount(int id);

}
