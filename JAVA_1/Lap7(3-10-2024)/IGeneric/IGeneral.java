package IGeneric;

import java.util.List;

public interface IGeneral<T>{
     T getById(int id);
     T getByCode(String code);
     public List<T> getByName(String keyword);
}
