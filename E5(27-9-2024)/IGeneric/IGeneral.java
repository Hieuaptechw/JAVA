package IGeneric;

import java.util.List;

public interface IGeneral<T> {
    T getById(int id);
    List<T> getByName(String name);
    List<T> sortByName();

}
