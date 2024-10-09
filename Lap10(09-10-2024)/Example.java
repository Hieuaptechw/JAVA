import Exceptions.UnusualTasteException;
import Entity.CoffeeCup;
import Entity.VirtualPerson;
import Services.VirtualCafe;

class Example {
    public static void main(String[] args) {
        CoffeeCup cup = new CoffeeCup();
        try {
            VirtualPerson cust = new VirtualPerson();
            VirtualCafe.serveCustomer(cust, cup);
        } catch (UnusualTasteException e) {
            System.out.println("This coffee has an unusual taste.");
            String message = e.getMessage();
            if (message != null) {
                System.out.println("Details: " + message);
            }
        }
    }
}
