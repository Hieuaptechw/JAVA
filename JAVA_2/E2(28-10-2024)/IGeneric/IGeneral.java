package IGeneric;

import java.util.Map;
import java.util.Set;

public interface IGeneral<T> {
    Set<T> searchByName(String deptName);
    Map<String, Set<T>> groupByName();
    Map<String, Long> countEmployees();
}
