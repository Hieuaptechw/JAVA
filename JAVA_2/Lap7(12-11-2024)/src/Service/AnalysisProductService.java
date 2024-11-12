package Service;

import Entity.AnalysisProduct;
import IGeneric.IGeneral;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AnalysisProductService implements IGeneral<AnalysisProduct> {
    private String fileIn;
    private String fileOut;
    private List<AnalysisProduct> analysisProducts;

    public AnalysisProductService(String fileIn, String fileOut) {
        this.analysisProducts = new ArrayList<>();
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }


    @Override
    public List<AnalysisProduct> readFile(){
        List<AnalysisProduct> analysisProducts = new ArrayList<>();
        try{
            BufferedReader reader   = new BufferedReader(new FileReader(fileIn));
            String lineData;
            while ((lineData = reader.readLine()) != null){
                AnalysisProduct analysisProductitem = new AnalysisProduct();
                if(!lineData.isEmpty()){
                    String[] parts = lineData.split(";");
                    analysisProductitem.setId(Integer.parseInt(parts[0]));
                    analysisProductitem.setProductId(Integer.parseInt(parts[1]));
                    analysisProductitem.setNumberOfClick(Integer.parseInt(parts[2]));
                    analysisProductitem.setNumberOfAddToCard(Integer.parseInt(parts[3]));
                    analysisProductitem.setNumberOfCheckout(Integer.parseInt(parts[4]));
                    analysisProductitem.setDate(LocalDate.parse(parts[5]));
                }
                analysisProducts.add(analysisProductitem);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return analysisProducts;
    }
    @Override
    public void writeFile(List<AnalysisProduct> analysisProducts) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            analysisProducts.stream()
                    .peek(p->{
                        try{
                            String lineWriter = p.toString(";");
                            bw.write(lineWriter);
                            bw.newLine();
                            bw.flush();

                        }catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                    }).collect(Collectors.toSet());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public Map<AnalysisProduct, Double> productPopular() {
        Map<Integer, Double> productPopularity = analysisProducts.stream()
                .collect(Collectors.groupingBy(
                        AnalysisProduct::getProductId,
                        Collectors.summingDouble(ap ->
                                ap.getNumberOfClick() * 1.0 +
                                        ap.getNumberOfAddToCard() * 0.5 +
                                        ap.getNumberOfCheckout() * 2.0
                        )
                ));
        Map<AnalysisProduct, Double> result = new HashMap<>();
        for (AnalysisProduct product : analysisProducts) {
            result.put(product, productPopularity.get(product.getProductId()));
        }
        System.out.println(result);
        return result;
    }
    public List<AnalysisProduct> productPopularValiabel(int productID,LocalDate startDate,LocalDate endDate,List<AnalysisProduct> products) {
        return products.stream()
                .filter(ap->ap.getProductId()==productID)
                .filter(ap->!ap.getDate().isBefore(startDate) && !ap.getDate().isAfter(endDate))
                .toList();

    }
//    public int sumClick(int productId,LocalDate startDate,LocalDate endDate) {
//        AtomicInteger sum = new AtomicInteger();
//
//                                        .forEach(ap->sum.addAndGet(ap.getNumberOfClick()));
//        System.out.println(sum.get());
//        return sum.get();
//    }
}
