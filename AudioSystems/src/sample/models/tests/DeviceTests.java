package sample.models.tests;

import org.junit.jupiter.api.Test;
import sample.models.Device;
import sample.models.InvalidFieldValueException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DeviceTests {

    private Device _sut=new Device();

    //check setters

    @Test
    void setDeviceId_PositiveValue_ShouldSetThisValue() throws InvalidFieldValueException {
        _sut.setDeviceId(3);
    }

    @Test
    void setDeviceId_ZeroValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(
                InvalidFieldValueException.class,
                ()->_sut.setDeviceId(0));
    }

    @Test
    void setDeviceId_NegativeValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(
                InvalidFieldValueException.class,
                ()->_sut.setDeviceId(-3));
    }

    @Test
    void setDeviceName_NotEmptyValue_ShouldSetThisValue() throws InvalidFieldValueException {
            _sut.setDeviceName("Abc");
    }

    @Test
    void setDeviceName_EmptyValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(InvalidFieldValueException.class,
                ()->_sut.setDeviceName(""));
    }

    @Test
    void setDeviceName_NullValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(InvalidFieldValueException.class,
                ()->_sut.setDeviceName(null));
    }

    @Test
    void  setPrice_PositiveValue_ShouldSetThisValue () throws InvalidFieldValueException {
        _sut.setPrice(2);
    }

    @Test
    void  setPrice_NegativeValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(InvalidFieldValueException.class,
                ()->_sut.setPrice(-4));
    }

    @Test
    void  setWeight_PositiveValue_ShouldSetThisValue () throws InvalidFieldValueException {
        _sut.setWeight(2);
    }

    @Test
    void  setWeight_NegativeValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(InvalidFieldValueException.class,
                ()->_sut.setWeight(-4));
    }
}
