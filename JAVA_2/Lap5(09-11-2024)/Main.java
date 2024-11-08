import Entity.*;
import Service.AutoService;
import Service.BetService;
import Service.PricingService;
import Service.SecuritiesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PricingService pricingService = new PricingService();

        List<Ticker> tickers = new ArrayList<>();
        List<Securities> securitys = new ArrayList<>();
        tickers.add(new Ticker(1, "AAPL", "NASDAQ", "Apple Inc."));
        tickers.add(new Ticker(2, "MSFT", "NASDAQ", "Microsoft Corporation"));
        tickers.add(new Ticker(3, "TSLA", "NASDAQ", "Tesla, Inc."));
        tickers.add(new Ticker(4, "AMZN", "NASDAQ", "Amazon.com, Inc."));
        securitys.add(new Securities(1,1, SecuritiesType.BOND));
        securitys.add(new Securities(2,1, SecuritiesType.STOCK));
        securitys.add(new Securities(3,1, SecuritiesType.COMMODITY));
        securitys.add(new Securities(4,2, SecuritiesType.BOND));
        securitys.add(new Securities(5,2, SecuritiesType.STOCK));
        securitys.add(new Securities(6,2, SecuritiesType.COMMODITY));
        List<Pricing> pricings = pricingService.readFromFile();
        List<User> users = new ArrayList<>();
        users.add(new User(1,"Hieuaptech",2321312));
        AutoService aS = new AutoService(tickers,pricings);
        BetService bS= new BetService(tickers,pricings,aS);
        Scanner sc = new Scanner(System.in);
        int choie;
        do{
            System.out.println("----- Hieu8888 -----");
            System.out.println("1. Bet");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choie = sc.nextInt();
            switch (choie){
                case 1:
                    bS.placeBet(users.get(0).getBalance());
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }while(choie!=4);
//        System.out.println("Initial Pricing Data:");
//        pricings.forEach(System.out::println);
//        AutoService aS = new AutoService(tickers, pricings);
//        SecuritiesService sT = new SecuritiesService(securitys,tickers,pricings);
//        aS.updatePrice();
//        Map<SecuritiesType, Double> totalPrices = sT.sumPricesByType();
//
//        totalPrices.forEach((type, totalPrice) ->
//                System.out.println(type + ": " + totalPrice));
//        System.out.println("\nUpdated Pricing Data:");
//        pricings.forEach(System.out::println);
    }
}
