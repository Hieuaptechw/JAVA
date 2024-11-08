package Service;

import Entity.Ticker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TickerService {
    private List<Ticker> tickerList;
    public TickerService(List<Ticker> tickerList) {
       this.tickerList = tickerList;
    }
    public Map<String, List<Ticker>> groupByExchange() {
        return tickerList.stream()
                .collect(Collectors.groupingBy(Ticker::getExchange));
    }
    public Map<String, Ticker> collectTickersToMap() {
        return tickerList.stream()
                .collect(Collectors.toMap(Ticker::getSymbol, t -> t));
    }
}
