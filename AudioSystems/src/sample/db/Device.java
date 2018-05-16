package sample.db;

import javafx.beans.property.*;

import java.security.InvalidParameterException;

public class Device {
    private ObjectProperty<Integer> deviceId=new SimpleObjectProperty<>();
    private StringProperty deviceName=new SimpleStringProperty();
    private ObjectProperty<Float> price=new SimpleObjectProperty<>();
    private ObjectProperty<Float> weight=new SimpleObjectProperty<>();
    private StringProperty description=new SimpleStringProperty();

    public Integer getDeviceId() {
        return deviceId.get();
    }

    public float getPrice() {
        return price.get();
    }

    public float getWeight() {
        return weight.get();
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public String getDescription() {
        return description.get();
    }

    public void setDeviceId(Integer deviceId) throws InvalidFieldValueException {
        if(deviceId>0) {
            this.deviceId.setValue(deviceId);
        } else {
            throw new InvalidFieldValueException("device id should be positive");
        }
    }

    public void setDeviceName(String deviceName) {
        this.deviceName.setValue(deviceName);
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public void setPrice(float price) throws IllegalArgumentException {
        if(price>=0){
            this.price.set(price);
        }else {
            throw new IllegalArgumentException("price should not be negative");
        }
    }

    public void setWeight(float weight) throws InvalidFieldValueException {
        if(weight>0) {
            this.weight.set(weight);
        }else{
            throw new InvalidFieldValueException("weight should not be negative");
        }
    }

    public ObjectProperty<Float> priceProperty() {
        return price;
    }

    public ObjectProperty<Float> weightProperty() {
        return weight;
    }

    public ObjectProperty<Integer> deviceIdProperty() {
        return deviceId;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty deviceNameProperty() {
        return deviceName;
    }
}
