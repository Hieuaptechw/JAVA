package Global;

import Exceptions.InvalidQuantityException;

import java.util.regex.Pattern;

public class OrderDetailValidator {
    private static Pattern orderDetailQuantityPattern = Pattern.compile("^[1-9][0-9]*$");
    public boolean quantityValidate(int quantity){
        return  orderDetailQuantityPattern.matcher(String.valueOf(quantity)).matches();
    }
}
