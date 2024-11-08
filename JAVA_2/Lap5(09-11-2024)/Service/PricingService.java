package Service;

import Entity.Pricing;
import Entity.Securities;
import Entity.SecuritiesType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PricingService {
    private List<Pricing> pricings;
    private static final String FILE_PATH = "src/Database/pricings.txt";
    public PricingService() {
        this.pricings = new ArrayList<>();
    }
    public List<Pricing> readFromFile() {
        List<Pricing> pricings = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(FILE_PATH))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                int tickerId = Integer.parseInt(parts[1]);
                LocalDateTime dateTime = LocalDateTime.parse(parts[2]);
                double openPrice = Double.parseDouble(parts[3]);
                double closePrice = Double.parseDouble(parts[4]);
                double currentPrice = Double.parseDouble(parts[5]);
                Pricing pricing = new Pricing(id, tickerId, dateTime, openPrice, closePrice, currentPrice);
                pricings.add(pricing);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pricings;
    }

    public double calculateAverageClosePricing(List<Pricing> pricings) {
    return pricings.stream()
            .mapToDouble(Pricing::getClosePrice)
            .average()
            .orElse(0.0);
    }
    public Map<Boolean, List<Pricing>> partitionByClosePrice() {
        return pricings.stream()
                .collect(Collectors.partitioningBy(p -> p.getClosePrice() > 100));
    }
    public Optional<Pricing> findHighestClosingPricing(List<Pricing> pricings) {
        return pricings.stream()
                .max(Comparator.comparing(Pricing::getClosePrice));
    }

}
