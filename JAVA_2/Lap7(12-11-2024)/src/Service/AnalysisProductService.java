package Service;

import Entity.AnalysisProduct;
import IGeneric.IGeneral;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AnalysisProductService implements IGeneral<AnalysisProduct> {
    private final String fileIn;
    private final String fileOut;

    public AnalysisProductService(String fileIn, String fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    @Override
    public List<AnalysisProduct> readFile() {
        List<AnalysisProduct> analysisProducts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            String lineData;
            while ((lineData = reader.readLine()) != null) {
                if (!lineData.isEmpty()) {
                    String[] parts = lineData.split(";");
                    if (parts.length == 6) {
                        AnalysisProduct analysisProductitem = new AnalysisProduct();
                        analysisProductitem.setId(Integer.parseInt(parts[0]));
                        analysisProductitem.setProductId(Integer.parseInt(parts[1]));
                        analysisProductitem.setNumberOfClick(Integer.parseInt(parts[2]));
                        analysisProductitem.setNumberOfAddToCard(Integer.parseInt(parts[3]));
                        analysisProductitem.setNumberOfCheckout(Integer.parseInt(parts[4]));
                        analysisProductitem.setDate(LocalDate.parse(parts[5]));
                        analysisProducts.add(analysisProductitem);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return analysisProducts;
    }

    @Override
    public void writeFile(List<AnalysisProduct> analysisProducts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut))) {
            for (AnalysisProduct p : analysisProducts) {
                String lineWriter = p.toString(";");
                bw.write(lineWriter);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Integer, Double> percentageAnalysisProduct(List<AnalysisProduct> products, LocalDate startDate, LocalDate endDate) {
        int totalClicks = totalClickOfAllProducts(products, startDate, endDate);
        Map<Integer, Integer> totalClickOfOneProduct = products.stream()
                .collect(Collectors.groupingBy(
                        AnalysisProduct::getProductId,
                        Collectors.summingInt(AnalysisProduct::getNumberOfClick)
                ));

        return totalClickOfOneProduct.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue() / (double) totalClicks) * 100
                ));
    }

    private int totalClickOfAllProducts(List<AnalysisProduct> products, LocalDate startDate, LocalDate endDate) {
        return products.stream()
                .filter(ap -> !ap.getDate().isBefore(startDate) && !ap.getDate().isAfter(endDate))
                .mapToInt(AnalysisProduct::getNumberOfClick)
                .sum();
    }
}
