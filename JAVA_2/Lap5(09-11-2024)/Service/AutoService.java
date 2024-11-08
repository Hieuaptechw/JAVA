package Service;

import Entity.Pricing;
import Entity.Ticker;
import com.sun.source.tree.WhileLoopTree;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Comparator;

public class AutoService {
    private List<Ticker> tickers;
    private List<Pricing> pricings;
    public AutoService(List<Ticker> tickers, List<Pricing> pricings) {
        this.tickers = tickers;
        this.pricings = pricings;
    }
    public List<Pricing> updatePrice() {
        Random rd = new Random();
        for (Ticker ticker : tickers) {
            double change = (rd.nextDouble() - 0.5) * 2;
            double lastPrice = getLastPrice(ticker.getId());
            double newPrice = lastPrice + change;
            double openPrice = getOpenPrice(ticker.getId());
            double closePrice = getClosePrice(ticker.getId());
            Pricing newPricing = new Pricing(pricings.size() + 1, ticker.getId(), java.time.LocalDateTime.now(), openPrice, closePrice, newPrice
            );
            pricings.add(newPricing);
        }
        return pricings;
    }
    public double getLastPrice(int ticker_id) {
        Optional<Pricing> pricingLast = pricings.stream()
                .filter(p -> p.getTickerId() == ticker_id)
                .max(Comparator.comparing(Pricing::getDateTime));
        return pricingLast.map(Pricing::getCurrentPrice).orElse(0.0);
    }
    public double getOpenPrice(int ticker_id) {
        Optional<Pricing> pricingOpen = pricings.stream()
                .filter(p -> p.getTickerId() == ticker_id)
                .findFirst();
        return pricingOpen.map(Pricing::getOpenPrice).orElse(0.0);
    }
    public double getClosePrice(int ticker_id) {
        Optional<Pricing> pricingOpen = pricings.stream()
                .filter(p -> p.getTickerId() == ticker_id)
                .findFirst();
        return pricingOpen.map(Pricing::getClosePrice).orElse(0.0);
    }
}
