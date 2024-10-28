package Validation;

public class Validation {
    public static  boolean isProductCode(String code){
        String preg = "^(MS|NE|SE)[0-9]{6}$";
        return code.matches(preg);
    }
    public static  boolean isProductName(String name){
        String preg = "^[a-zA-Z]+$";
        return name.matches(preg);
    }
    public static  boolean isCustomerName(String name){
        String preg = "^[a-zA-Z]{3,50}$";
        return name.matches(preg);
    }
    public static  boolean isOrderCode(String code){
        String preg = "^(ORDPM)[0-9]{8}$";
        return code.matches(preg);
    }
    public static  boolean isNumberInterger(String id){
        String preg = "^[1-9][0-9]*$";
        return id.matches(preg);
    }

}
