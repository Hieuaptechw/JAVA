package Services;

import Exceptions.TooColdException;
import Exceptions.TooHotException;
import Exceptions.UnusualTasteException;
import Entity.CoffeeCup;
import Entity.VirtualPerson;

public class VirtualCafe {
    public static void serveCustomer(VirtualPerson cust, CoffeeCup cup) throws UnusualTasteException {
        try {
            cust.drinkCoffee(cup);
            System.out.println("Coffee tastes just right.");
        } catch (TooColdException e) {
            System.out.println("Coffee is too cold. Temperature: " + e.getTemperature() + "°C");
        } catch (TooHotException e) {
            System.out.println("Coffee is too hot. Temperature: " + e.getTemperature() + "°C");
        } catch (UnusualTasteException e) {
            System.out.println("Customer is complaining of an unusual taste.");
            String message = e.getMessage();
            if (message != null) {
                System.out.println(message);
            }
        }
    }
}
