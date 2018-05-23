package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.collections.AudioSystemService;
import sample.collections.MySqlCustomerRepository;
import sample.collections.MySqlDeviceRepository;
import sample.collections.MySqlProductRepository;
import sample.models.*;

import java.sql.*;

//controller for main form
public class Controller {

    //access db service
    private AudioSystemService service;

    //customer input controls
    public TextField customerNameTextField;
    public Label customerIdLabel;

    //device input controls
    public TextField deviceNameTextField;
    public Label deviceIdLabel;
    public TextField priceTextField;
    public TextField weightTextField;
    public TextArea descriptionTextArea;

    //product input controls
    public TextField deviceIdTextField;
    public Label productIdLabel;
    public TextField customerIdTextField;

    //customer table
    public TableView<Customer> customersView;
    public TableColumn<Customer, Integer> customerIdColumn;
    public TableColumn<Customer, String> customerNameColumn;

    //device table
    public TableView<Device> devicesView;
    public TableColumn<Device, Integer> deviceIdColumn;
    public TableColumn<Device, String> deviceNameColumn;
    public TableColumn<Device, Float> priceColumn;
    public TableColumn<Device, Float> weightColumn;
    public TableColumn<Device, String> descriptionColumn;

    //product table
    public TableView<Product> productsView;
    public TableColumn<Product, Integer> productIdColumn;
    public TableColumn<Product, Integer> productCustomerIdColumn;
    public TableColumn<Product, Integer> productDeviceIdColumn;

    public Controller() {
        //initialize service
        try {
            service = new AudioSystemService(
                    new MySqlCustomerRepository(),
                    new MySqlDeviceRepository(),
                    new MySqlProductRepository()
            );
            //show message if exception occurs
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void initialize() {
        //set cells values factories for each table
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());

        //also, add listeners, that change input controls content
        //depending on selected row int table
        customersView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                customerIdLabel.setText((String.valueOf(newSelection.getCustomerId())));
                customerNameTextField.setText(newSelection.getCustomerName());
            }
        });

        //same operations for other tables
        deviceIdColumn.setCellValueFactory(c -> c.getValue().deviceIdProperty());
        deviceNameColumn.setCellValueFactory(c -> c.getValue().deviceNameProperty());
        priceColumn.setCellValueFactory(c -> c.getValue().priceProperty());
        weightColumn.setCellValueFactory(c -> c.getValue().weightProperty());
        descriptionColumn.setCellValueFactory(c -> c.getValue().descriptionProperty());
        devicesView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                deviceIdLabel.setText(String.valueOf(newSelection.getDeviceId()));
                deviceNameTextField.setText(newSelection.getDeviceName());
                priceTextField.setText(String.valueOf(newSelection.getPrice()));
                weightTextField.setText(String.valueOf(newSelection.getWeight()));
                descriptionTextArea.setText(newSelection.getDescription());
            }
        });

        productIdColumn.setCellValueFactory(c -> c.getValue().productIdProperty());
        productCustomerIdColumn.setCellValueFactory(c -> c.getValue().customerIdProperty());
        productDeviceIdColumn.setCellValueFactory(c -> c.getValue().deviceIdProperty());
        productsView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                productIdLabel.setText(String.valueOf(newSelection.getProductId()));
                customerIdTextField.setText(String.valueOf(newSelection.getCustomerId()));
                deviceIdTextField.setText(String.valueOf(newSelection.getDeviceId()));
            }
        });

        //load tables from db
        load();
    }

    private void load() {
        try {
            //get data and set it to table views
            customersView.setItems(service.getCustomers());
            devicesView.setItems(service.getDevices());
            productsView.setItems(service.getProducts());
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    //add, remove, update from objects input forms
    //using audio systems service

    public void addCustomerButtonClick(ActionEvent actionEvent) {
        try {
            Customer customer = new Customer();
            customer.setCustomerName(customerNameTextField.getText());
            service.insertCustomer(customer);
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void removeCustomerButtonClick(ActionEvent actionEvent) {
        try {
            service.deleteCustomer(customersView.getSelectionModel().getSelectedItem());
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void updateCustomerButtonClick(ActionEvent actionEvent) {
        try {
            Customer customer = customersView.getSelectionModel().getSelectedItem();
            customer.setCustomerName(customerNameTextField.getText());
            service.updateCustomer(customer);
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void addDeviceButtonClick(ActionEvent actionEvent) {
        try {
            Device device = new Device();
            device.setDeviceName(deviceNameTextField.getText());
            device.setDescription(descriptionTextArea.getText());
            device.setPrice(Float.valueOf(priceTextField.getText()));
            device.setWeight(Float.valueOf(weightTextField.getText()));
            service.insertDevice(device);
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void removeDeviceButtonClick(ActionEvent actionEvent) {
        try {
            service.deleteDevice(devicesView.getSelectionModel().getSelectedItem());
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void updateDeviceButtonClick(ActionEvent actionEvent) {
        try {
            Device device = devicesView.getSelectionModel().getSelectedItem();
            device.setDeviceName(deviceNameTextField.getText());
            device.setDescription(descriptionTextArea.getText());
            device.setPrice(Float.valueOf(priceTextField.getText()));
            device.setWeight(Float.valueOf(weightTextField.getText()));
            service.updateDevice(device);
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void addProductButtonClick(ActionEvent actionEvent) {
        try {
            Product product = new Product();
            product.setDeviceId(Integer.valueOf(deviceIdTextField.getText()));
            try { product.setCustomerId(Integer.valueOf(customerIdTextField.getText())); } catch (Exception ignored) { }
            service.insertProduct(product);
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void removeProductButtonClick(ActionEvent actionEvent) {
        try {
            service.deleteProduct(productsView.getSelectionModel().getSelectedItem());
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void updateProductButtonClick(ActionEvent actionEvent) {
        try {
            Product product = productsView.getSelectionModel().getSelectedItem();
            product.setDeviceId(Integer.valueOf(deviceIdTextField.getText()));
            try { product.setCustomerId(Integer.valueOf(customerIdTextField.getText())); }
            catch (Exception ignored) {}
            service.updateProduct(product);
            //show message if exception occurs
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }
}
