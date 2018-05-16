package sample.db;

import javafx.beans.property.*;

public class Product {
    private ObjectProperty<Integer> productId=new SimpleObjectProperty<>();
    private ObjectProperty<Integer> deviceId=new SimpleObjectProperty<>();
    private ObjectProperty<Integer> customerId =new SimpleObjectProperty<>();

    public Integer getProductId() {
        return productId.get();
    }

    public Integer getDeviceId() {
        return deviceId.get();
    }

    public Integer getCustomerId() {
        return customerId.get();
    }

    public void setProductId(Integer productId) throws InvalidFieldValueException {
        if(productId>0) {
            this.productId.setValue(productId);
        } else {
            throw new InvalidFieldValueException("product id should be positive");
        }
    }

    public void setCustomerId(Integer customer) throws InvalidFieldValueException {
        if(customer>0) {
            this.customerId.setValue(customer);
        } else {
            throw new InvalidFieldValueException("customer id should be positive");
        }
    }

    public void setDeviceId(Integer deviceId) throws InvalidFieldValueException {
        if(deviceId>0) {
            this.deviceId.setValue(deviceId);
        } else {
            throw new InvalidFieldValueException("device id should be positive");
        }
    }

    public ObjectProperty<Integer> productIdProperty() {
        return productId;
    }

    public ObjectProperty<Integer> deviceIdProperty() {
        return deviceId;
    }

    public ObjectProperty<Integer> customerIdProperty() {
        return customerId;
    }
}
