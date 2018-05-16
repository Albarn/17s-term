package sample.db;

import javafx.beans.property.*;

import java.security.InvalidParameterException;

public class Customer {
    private ObjectProperty<Integer> customerId=new SimpleObjectProperty<>();
    private StringProperty customerName=new SimpleStringProperty();

    public int getCustomerId() {
        return customerId.get();
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerId(int customerId) throws InvalidFieldValueException {
        if(customerId>0) {
            this.customerId.setValue(customerId);
        }
        else {
            throw new InvalidFieldValueException("customer id should be positive");
        }
    }

    public void setCustomerName(String customerNameProperty) {
        this.customerName.setValue(customerNameProperty);
    }

    public ObjectProperty<Integer> customerIdProperty() {
        return customerId;
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }
}
