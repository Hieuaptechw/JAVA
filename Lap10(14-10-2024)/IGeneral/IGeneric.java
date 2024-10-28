package IGeneral;

import java.util.List;
public interface IGeneric <T>{
    T getById(int id);
    List <T> getByName(String name);
    List<T> getAll();
    void add(T t);
    T update(T t);
    void delete(T t);
}
