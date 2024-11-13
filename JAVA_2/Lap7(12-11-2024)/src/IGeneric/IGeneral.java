package IGeneric;

import Entity.AnalysisProduct;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IGeneral<T> {
    List<T> readFile();
    void writeFile(List<T> t);
    Map<Integer, Double> percentageAnalysisProduct(List<T> t, LocalDate startDate, LocalDate endDate);

}