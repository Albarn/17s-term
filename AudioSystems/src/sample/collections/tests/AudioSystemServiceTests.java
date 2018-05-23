package sample.collections.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.collections.AudioSystemService;
import sample.models.Customer;
import sample.models.Device;
import sample.models.InvalidFieldValueException;
import sample.models.Product;

import static org.junit.jupiter.api.Assertions.*;

class AudioSystemServiceTests {

    private FakeRepository<Customer> customers;
    private FakeRepository<Device> devices;
    private FakeRepository<Product> products;
    private AudioSystemService _sut;

    @BeforeEach
    void setUp() throws InvalidFieldValueException {
        Customer c1=new Customer();
        c1.setCustomerName("Kenny");
        c1.setCustomerId(1);
        Customer c2=new Customer();
        c2.setCustomerName("Bonny");
        c2.setCustomerId(2);
        customers=new FakeRepository<>( new Customer[]{ c1,c2 } );

        Device d1=new Device();
        d1.setDeviceName("Radio");
        d1.setDeviceId(1);
        Device d2=new Device();
        d2.setDeviceName("Super");
        d2.setDeviceId(2);
        Device d3=new Device();
        d3.setDeviceName("Duper");
        d3.setDeviceId(3);
        devices=new FakeRepository<>(new Device[]{d1,d2,d3});

        Product p1=new Product();
        p1.setProductId(1);
        p1.setDeviceId(1);
        products=new FakeRepository<>(new Product[]{p1});

        _sut=new AudioSystemService(customers,devices,products);
    }

    @Test
    void getCustomers_SeveralRowsInStorage_ShouldReturnTheSameAmountOfRows() throws Exception {
        assertEquals(customers.items.size(), _sut.getCustomers().size());
    }

    @Test
    void getCustomers_BrokenRepository_ShouldThrowException(){
        customers.broken =true;
        assertThrows(Exception.class,()->_sut.getCustomers());
    }

    @Test
    void getDevices_SeveralRowsInStorage_ShouldReturnTheSameAmountOfRows() throws Exception {
        assertEquals(devices.items.size(), _sut.getDevices().size());
    }

    @Test
    void getDevices_BrokenRepository_ShouldThrowException(){
        devices.broken =true;
        assertThrows(Exception.class,()->_sut.getDevices());
    }

    @Test
    void getProducts_SeveralRowsInStorage_ShouldReturnTheSameAmountOfRows() throws Exception {
        assertEquals(products.items.size(), _sut.getProducts().size());
    }

    @Test
    void getProducts_BrokenRepository_ShouldThrowException(){
        products.broken =true;
        assertThrows(Exception.class,()->_sut.getProducts());
    }

    @Test
    void insertCustomer_EmptyRow_NumberOfRowsInStorageShouldIncrease() throws Exception {
        int oldSize=customers.items.size();
        _sut.insertCustomer(new Customer());
        assertEquals(oldSize+1,customers.items.size());
    }

    @Test
    void insertDevice_EmptyRow_NumberOfRowsInStorageShouldIncrease() throws Exception {
        int oldSize=devices.items.size();
        _sut.insertDevice(new Device());
        assertEquals(oldSize+1,devices.items.size());
    }

    @Test
    void insertProduct_EmptyRow_NumberOfRowsInStorageShouldIncrease() throws Exception {
        int oldSize=products.items.size();
        _sut.insertProduct(new Product());
        assertEquals(oldSize+1,products.items.size());
    }

    @Test
    void deleteCustomer_FirstRow_NumberOfRowsInStorageShouldDecrease() throws Exception {
        int oldSize=customers.items.size();
        _sut.deleteCustomer(customers.items.get(0));
        assertEquals(oldSize-1,customers.items.size());
    }

    @Test
    void deleteDevice_FirstRow_NumberOfRowsInStorageShouldDecrease() throws Exception {
        int oldSize=devices.items.size();
        _sut.deleteDevice(devices.items.get(0));
        assertEquals(oldSize-1,devices.items.size());
    }

    @Test
    void deleteProduct_FirstRow_NumberOfRowsInStorageShouldDecrease() throws Exception {
        int oldSize=products.items.size();
        _sut.deleteProduct(products.items.get(0));
        assertEquals(oldSize-1,products.items.size());
    }
}