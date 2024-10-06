package IGeneric;

import java.util.List;

public interface ICustomer<C>{
    List<C> getCustomerValid();
    List<C> getCustomerNotValid();
}
