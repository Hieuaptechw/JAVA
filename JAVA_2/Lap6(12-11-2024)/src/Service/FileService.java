package Service;

import Entity.CRIndex;
import Entity.CRStatistics;
import Entity.StatisticsView;
import IGeneric.IFileService;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileService implements IFileService<StatisticsView> {
    private String fileIn;
    private String fileOut;
    public FileService(String fileIn, String fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    public List<StatisticsView> readFileStatistics(String fileInPath) {
        List<StatisticsView> statisticsViews = new ArrayList();

        String line;
        StatisticsView statistics;
        try {
            for(BufferedReader reader = new BufferedReader(new FileReader(fileInPath)); (line = reader.readLine()) != null; statisticsViews.add(statistics)) {
                statistics = new StatisticsView();
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    statistics.setProductId(Integer.parseInt(parts[0]));
                    statistics.setView(Integer.parseInt(parts[1]));
                    statistics.setNumberOfAddToCard(Integer.parseInt(parts[2]));
                    statistics.setNumberOfCheckout(Integer.parseInt(parts[3]));
                    statistics.setDate(LocalDate.parse(parts[4]));
                }
            }
        } catch (Exception var7) {
            Exception e = var7;
            System.out.println(e.getMessage());
        }

        return statisticsViews;
    }

    public void writeFileStatistics(String fileOutPath, List<CRStatistics> crStatistics) {
        DecimalFormat df = new DecimalFormat("#.###");
        List<CRIndex> crIndexes = crStatistics.stream()
                .map(cr -> {
                    double indexAddTo = (double) cr.getTotalAddTo() / cr.getTotalView();
                    double indexCheckOut = (double) cr.getTotalCheckOut() / cr.getTotalView();
                    indexAddTo = Double.parseDouble(df.format(indexAddTo));
                    indexCheckOut = Double.parseDouble(df.format(indexCheckOut));
                    return new CRIndex(
                            cr.getId(),
                            cr.getMonth(),
                            cr.getYear(),
                            indexAddTo,
                            indexCheckOut
                    );
                })
                .collect(Collectors.toList());

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOutPath));
            crIndexes.stream()
                    .peek(c->{
                        try{
                            String lineWriter = c.toString(";");
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



}
