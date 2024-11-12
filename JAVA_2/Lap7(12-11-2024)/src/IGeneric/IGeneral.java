package IGeneric;

import Entity.AnalysisProduct;

import java.util.List;

public interface IGeneral<T> {
    List<T> readFile();
    void writeFile(List<T> t);

}