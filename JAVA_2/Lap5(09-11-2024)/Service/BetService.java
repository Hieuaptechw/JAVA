package Service;

import Entity.Pricing;
import Entity.Ticker;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BetService {
    private List<Ticker> tickers;
    private List<Pricing> pricings;
    private AutoService aS;

    public BetService(List<Ticker> tickers, List<Pricing> pricings, AutoService aS) {
        this.tickers = tickers;
        this.pricings = pricings;
        this.aS = aS;
    }

    public void placeBet(double balance) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tickers:");
        for (int i = 0; i < tickers.size(); i++) {
            System.out.println((i + 1) + ". " + tickers.get(i).getSymbol());
        }
        System.out.print("Choose a ticker by number: ");
        int tickerChoice = sc.nextInt() - 1;
        if (tickerChoice < 0 || tickerChoice >= tickers.size()) {
            System.out.println("Invalid ticker choice. Please try again.");
            return;
        }
        Ticker selectedTicker = tickers.get(tickerChoice);

        int tickerId = selectedTicker.getId();
        System.out.print("Enter the amount to bet: ");
        double betAmount = sc.nextDouble();
        if (betAmount <= balance) {
            balance -= betAmount;
            System.out.println("Bet placed successfully! Remaining balance: " + balance);
            System.out.print("Choose bet direction (1 for Up, 2 for Down): ");
            int betChoose = sc.nextInt();
            double priceCurrent = getLastPrice(tickerId);
            String betDirection = "";
            if (betChoose == 1) {
                betDirection = "Up";
                System.out.println("You bet 'Up' on ticker " + selectedTicker.getSymbol());
            } else if (betChoose == 2) {
                betDirection = "Down";
                System.out.println("You bet 'Down' on ticker " + selectedTicker.getSymbol());
            } else {
                System.out.println("Invalid bet direction.");
                return;
            }

            aS.updatePrice();
            double afterUpdate = getLastPrice(tickerId);
            String result = "";
            if ((betDirection.equals("Up") && afterUpdate > priceCurrent) ||
                    (betDirection.equals("Down") && afterUpdate < priceCurrent)) {
                result = "Win";
                balance += betAmount * 2;
            } else {
                result = "Lose";
            }
            System.out.printf("| %-10s | %-10.2f | %-10.2f | %-10.2f | %-10s |\n",
                    selectedTicker.getSymbol(), priceCurrent, afterUpdate, betAmount, result);
            System.out.println("Balance: " + balance);

        } else {
            System.out.println("Insufficient funds to place the bet.");
        }
    }

    public double getLastPrice(int tickerId) {
        Optional<Pricing> pricingLast = pricings.stream()
                .filter(p -> p.getTickerId() == tickerId)
                .max(Comparator.comparing(Pricing::getDateTime));

        return pricingLast.map(Pricing::getCurrentPrice).orElse(0.0);
    }
}
