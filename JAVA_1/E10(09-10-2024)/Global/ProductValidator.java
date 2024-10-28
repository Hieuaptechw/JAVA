package Global;

import Exceptions.InvalidProducIdException;
import Exceptions.InvalidProductNameException;
import Exceptions.InvalidQuantityException;

import java.util.regex.Pattern;

public class ProductValidator {
    private static Pattern productIdPattern = Pattern.compile("^(MS|NE|SE)[0-9]{6}$");
    private static Pattern productNamePattern = Pattern.compile("^[a-zA-Z\\s]+$");
    private static Pattern productQuantityPattern = Pattern.compile("^[1-9][0-9]*$");

    public boolean idValidate(String id) {
        return productIdPattern.matcher(id).matches();
    }

    public boolean nameValidate(String name) {
        return productNamePattern.matcher(name).matches();
    }

    public boolean quantityValidate(int quantity) {
        String quantityStr = String.valueOf(quantity);
        return productQuantityPattern.matcher(quantityStr).matches();
    }
}
