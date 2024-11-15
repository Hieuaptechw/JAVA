package IGeneric;

import java.util.List;
import java.util.Map;

public interface IFileService<T> {
    List<T> readFileStatistics(String fileInPath);
//    void writeFileStatistics(String fileOutPath,List<T> t);
}
