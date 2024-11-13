package Controller;

import Entity.AnalysisProduct;
import Service.AnalysisProductService;

import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnalysisProductController {
    private final AnalysisProductService aPS;

    public AnalysisProductController(AnalysisProductService aPS) {
        this.aPS = aPS;
    }

    public List<AnalysisProduct> readFile() {
        List<AnalysisProduct> analysisProductList = aPS.readFile();
        analysisProductList.forEach(ap -> System.out.println(ap.toString("-")));
        return analysisProductList;
    }
    public void writeFile(List<AnalysisProduct> analysisProductList) {
        aPS.writeFile(analysisProductList);
    }

    public void analysisProduct(List<AnalysisProduct> analysisProductList) {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 30);
        Map<Integer, Double> percentageAnalysisProduct = aPS.percentageAnalysisProduct(analysisProductList, startDate, endDate);
        percentageAnalysisProduct.forEach((productId, percentage) ->
                System.out.printf("| %-6d | %10.2f%% |\n", productId, percentage));
        Map.Entry<Integer, Double> maxEntry = percentageAnalysisProduct.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        System.out.println("Product trending id:"+ maxEntry.getKey() +  "-"+ String.format("%.2f", maxEntry.getValue())+"%");
    }

}
