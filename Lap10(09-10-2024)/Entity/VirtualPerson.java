package Entity;

import Exceptions.TooColdException;
import Exceptions.TooHotException;
import Exceptions.UnusualTasteException;

public class VirtualPerson {
    private static final int tooCold = 65;
    private static final int tooHot = 85;

    public void drinkCoffee(CoffeeCup cup) throws TooColdException, TooHotException, UnusualTasteException {
        int temperature = cup.getTemperature();
        if (temperature <= tooCold) {
            throw new TooColdException(temperature);
        } else if (temperature >= tooHot) {
            throw new TooHotException(temperature);
        }
        if (temperature == 80) {
            throw new UnusualTasteException("This coffee has a sweet taste.");
        }
    }
}
