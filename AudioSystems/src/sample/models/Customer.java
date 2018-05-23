package sample.models;

import javafx.beans.property.*;

//customer who make orders for devices
public class Customer {
    //customer id number and his name
    private ObjectProperty<Integer> customerId=new SimpleObjectProperty<>();
    private StringProperty customerName=new SimpleStringProperty();

    //properties and fields accessors
    public int getCustomerId() { return customerId.get(); }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerId(int customerId) throws InvalidFieldValueException {
        //customer id should be positive
        if(customerId>0) {
            this.customerId.setValue(customerId);
        }
        else {
            throw new InvalidFieldValueException("customer id should be positive");
        }
    }

    public void setCustomerName(String customerName) throws InvalidFieldValueException {
        if(customerName!=null && !customerName.equals(""))
        {
            this.customerName.setValue(customerName);
        }
        else {
            throw new InvalidFieldValueException("customer name should not be empty");
        }
    }

    public ObjectProperty<Integer> customerIdProperty() {
        return customerId;
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }
}
