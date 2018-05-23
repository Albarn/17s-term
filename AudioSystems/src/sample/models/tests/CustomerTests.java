package sample.models.tests;

import org.junit.jupiter.api.Test;
import sample.models.Customer;
import sample.models.InvalidFieldValueException;

import static org.junit.jupiter.api.Assertions.assertThrows;
class CustomerTests {

    private Customer _sut=new Customer();

    //check setters

    @Test
    void setCustomerId_PositiveValue_ShouldSetThisValue() throws InvalidFieldValueException {
        _sut.setCustomerId(3);
    }

    @Test
    void setCustomerId_ZeroValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(
                InvalidFieldValueException.class,
                ()->_sut.setCustomerId(0));
    }

    @Test
    void setCustomerId_NegativeValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(
                InvalidFieldValueException.class,
                ()->_sut.setCustomerId(-3));
    }

    @Test
    void setCustomerName_NotEmptyValue_ShouldSetThisValue() throws InvalidFieldValueException {
        _sut.setCustomerName("Abc");
    }

    @Test
    void setCustomerName_EmptyValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(InvalidFieldValueException.class,
                ()->_sut.setCustomerName(""));
    }

    @Test
    void setCustomerName_NullValue_ShouldThrowInvalidFieldValueException(){
        assertThrows(InvalidFieldValueException.class,
                ()->_sut.setCustomerName(null));
    }
}
