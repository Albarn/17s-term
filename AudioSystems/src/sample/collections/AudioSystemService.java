package sample.collections;

import javafx.collections.ObservableList;
import sample.models.Customer;
import sample.models.Device;
import sample.models.Product;

public class AudioSystemService {
    private Repository<Customer> customers;
    private Repository<Device> devices;
    private Repository<Product> products;

    public AudioSystemService(Repository<Customer> customers,
                              Repository<Device> devices,
                              Repository<Product> products) {
        this.customers = customers;
        this.devices = devices;
        this.products = products;
    }

    public ObservableList<Customer> getCustomers() throws Exception {
        return customers.getAll();
    }

    public ObservableList<Device> getDevices() throws Exception {
        return devices.getAll();
    }

    public ObservableList<Product> getProducts() throws Exception {
        return products.getAll();
    }

    public int insertCustomer(Customer customer) throws Exception {
        return customers.insert(customer);
    }

    public int insertDevice(Device device) throws Exception {
        return devices.insert(device);
    }

    public int insertProduct(Product product) throws Exception {
        return  products.insert(product);
    }

    public void updateCustomer(Customer customer) throws Exception{
        customers.update(customer);
    }

    public void updateDevice(Device device) throws Exception{
        devices.update(device);
    }

    public void updateProduct(Product product) throws Exception{
        products.update(product);
    }

    public void deleteCustomer(Customer customer) throws Exception{
        customers.delete(customer);
    }

    public void deleteDevice(Device device) throws Exception{
        devices.delete(device);
    }

    public void deleteProduct(Product product) throws Exception{
        products.delete(product);
    }
}
