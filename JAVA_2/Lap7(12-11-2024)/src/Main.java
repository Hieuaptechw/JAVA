import Controller.AnalysisProductController;
import Entity.AnalysisProduct;
import Service.AnalysisProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String sysPath = System.getProperty("user.dir");
        String dataFileIn = sysPath.replace("/","\\")   +"/Database/analysisproduct.in.txt";
        String dataFileOut = sysPath.replace("/","\\")   +"/Database/analysisproduct.out.txt";
        AnalysisProductService aPS = new AnalysisProductService(dataFileIn,dataFileOut);
        AnalysisProductController aC = new AnalysisProductController(aPS);
        List<AnalysisProduct> analysisProducts = aC.readFile();
        aC.analysisProduct(analysisProducts);
        aC.writeFile(analysisProducts);
    }
}