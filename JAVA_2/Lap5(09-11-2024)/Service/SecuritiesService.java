package Service;

import Entity.Pricing;
import Entity.Securities;
import Entity.SecuritiesType;
import Entity.Ticker;

import java.util.*;
import java.util.stream.Collectors;

public class SecuritiesService {
    private List<Securities> securitiesList;
    private List<Pricing> pricingList;
    private List<Ticker> tickerList;
    public SecuritiesService(List<Securities> securitiesList, List<Ticker> tickerList, List<Pricing> pricingList) {
        this.securitiesList = securitiesList;
        this.tickerList = tickerList;
        this.pricingList = pricingList;
    }
    public List<Securities> filteringSecurities(SecuritiesType securityType) {
        return securitiesList.stream()
                .filter(s -> s.getSecurityType().equals(securityType))
                .collect(Collectors.toList());
    }
    public Map<SecuritiesType, Double> sumPricesByType() {
        return securitiesList.stream()
                .collect(Collectors.groupingBy(
                        Securities::getSecurityType,
                        Collectors.summingDouble(s -> getLastPrice(s.getTickerId()))
                ));
    }

    public double getLastPrice(int tickerId) {
        Optional<Pricing> pricingLast = pricingList.stream()
                .filter(p -> p.getTickerId() == tickerId)
                .max(Comparator.comparing(Pricing::getDateTime));
        return pricingLast.map(Pricing::getCurrentPrice).orElse(0.0);
    }

}
