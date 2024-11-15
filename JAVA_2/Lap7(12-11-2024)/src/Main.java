import Entity.CRStatistics;
import Entity.StatisticsView;
import Service.CRStatisticsService;
import Service.FileService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String sysPath = System.getProperty("user.dir");
        String dataFileIn = sysPath.replace("/","\\")   +"/Database/product.in.txt";
        String dataFileOut = sysPath.replace("/","\\")   +"/Database/product.out.txt";
        System.out.println(dataFileIn);
        FileService fileService = new FileService(dataFileIn,dataFileOut);
        List<StatisticsView> statisticsViews = fileService.readFileStatistics(dataFileIn);
        CRStatisticsService crStatisticsService = new CRStatisticsService(statisticsViews);
        List<CRStatistics> crStatistics = new ArrayList<>(crStatisticsService.dataCRS().values());
        fileService.writeFileStatistics(dataFileOut,crStatistics);
    }
}