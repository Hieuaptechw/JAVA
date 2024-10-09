package Global;

import java.util.regex.Pattern;

public class CustomerValidator {
    private static Pattern customerNamePattern = Pattern.compile("^[a-zA-Z]{3,50}$");
    private static Pattern customerIdPattern = Pattern.compile("^[1-9][0-9]*$");
    public boolean nameValidate(String name) {
        return customerNamePattern.matcher(name).matches();
    }
    public boolean idValidate(int id) {
        String idStr = String.valueOf(id);
        return customerIdPattern.matcher(idStr).matches();
    }
}
