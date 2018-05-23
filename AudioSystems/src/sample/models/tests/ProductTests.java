package sample.models.tests;

import org.junit.jupiter.api.Test;
import sample.models.InvalidFieldValueException;
import sample.models.Product;

import static org.junit.jupiter.api.Assertions.assertThrows;
class ProductTests {

    private Product _sut=new Product();

    //check setters

    @Test
    void setProductId_PositiveValue_ShouldSetThisValue() throws InvalidFieldValueException {
        _sut.setProductId(3);
    }

    @Test
    void setProductId_ZeroValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(
                InvalidFieldValueException.class,
                ()->_sut.setProductId(0));
    }

    @Test
    void setProductId_NegativeValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(
                InvalidFieldValueException.class,
                ()->_sut.setProductId(-3));
    }

    @Test
    void setCustomerId_PositiveValue_ShouldSetThisValue() throws InvalidFieldValueException {
        _sut.setCustomerId(3);
    }

    @Test
    void setCustomerId_ZeroValue_ShouldSetThisValue() throws InvalidFieldValueException {
        _sut.setCustomerId(0);
    }

    @Test
    void setCustomerId_NegativeValue_ShouldSetThisValue() throws InvalidFieldValueException {
        _sut.setCustomerId(-3);
    }

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
}
