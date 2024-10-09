package Global;

import Exceptions.InvalidOrderIdException;

import java.util.regex.Pattern;

public class OrderValidator {
    private static Pattern orderIdPattern = Pattern.compile("^ORDPM[0-9]{8}$");
    public boolean idValidate(String id) {
        return orderIdPattern.matcher(id).matches();
    }
}
