package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.collections.*;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import sample.db.*;

import java.sql.*;

public class Controller {
    private static Connection connection;
    private static String JDBC_URL =
            "jdbc:mysql://localhost:3306/audio_systems?user=root&password=2501&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Statement st;
    private ObservableList<Customer> customersData = FXCollections.observableArrayList();

    public TableView<Customer> customersView;
    public TableColumn<Customer,Integer> customerIdColumn;
    public TableColumn<Customer,String> customerNameColumn;

    public TableView<Device> devicesView;
    public TableColumn<Device,Integer> deviceIdColumn;
    public TableColumn<Device,String> deviceNameColumn;
    public TableColumn<Device,Float> priceColumn;
    public TableColumn<Device,Float> weightColumn;
    public TableColumn<Device,String> descriptionColumn;

    public TableView<Product> productsView;
    public TableColumn<Product,Integer> productIdColumn;
    public TableColumn<Product,Integer> productCustomerIdColumn;
    public TableColumn<Product,Integer> productDeviceIdColumn;

    public Controller(){
        //load driver, initialize connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =
                    DriverManager.getConnection(JDBC_URL);
            st = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        //bind columns to fields

    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        customerNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        deviceIdColumn.setCellValueFactory(c->c.getValue().deviceIdProperty());
        deviceNameColumn.setCellValueFactory(c->c.getValue().deviceNameProperty());
        deviceNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellValueFactory(c->c.getValue().priceProperty());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        weightColumn.setCellValueFactory(c->c.getValue().weightProperty());
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        descriptionColumn.setCellValueFactory(c->c.getValue().descriptionProperty());
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        productIdColumn.setCellValueFactory(c->c.getValue().productIdProperty());
        productCustomerIdColumn.setCellValueFactory(c->c.getValue().customerIdProperty());
        productCustomerIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productDeviceIdColumn.setCellValueFactory(c->c.getValue().deviceIdProperty());
        productDeviceIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        load();
    }

    private void load() {

        try {
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            ResultSet rs = st.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                Customer row = new Customer();
                row.setCustomerId(rs.getInt("CustomerId"));
                row.setCustomerName(rs.getString("CustomerName"));
                customers.add(row);
            }
            customersView.setItems(customers);

            ObservableList<Device> devices=FXCollections.observableArrayList();
            rs=st.executeQuery("select * from device");
            while (rs.next()){
                Device row=new Device();
                row.setDeviceId(rs.getInt("DeviceId"));
                row.setDescription(rs.getString("Description"));
                row.setDeviceName(rs.getString("DeviceName"));
                row.setPrice(rs.getFloat("Price"));
                row.setWeight(rs.getFloat("Weight"));
                devices.add(row);
            }
            devicesView.setItems(devices);

            ObservableList<Product> products=FXCollections.observableArrayList();
            rs=st.executeQuery("select * from product");
            while (rs.next()){
                Product row=new Product();
                try {
                    row.setCustomerId(rs.getInt("CustomerId"));
                }catch (Exception ignored){}
                row.setDeviceId(rs.getInt("DeviceId"));
                row.setProductId(rs.getInt("ProductId"));
                products.add(row);
            }
            productsView.setItems(products);
        } catch (SQLException | InvalidFieldValueException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void addCustomerButtonClick(ActionEvent actionEvent) {
        try {
            st.execute("insert into customer values(null, \"new\")");
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void removeCustomerButtonClick(ActionEvent actionEvent) {
        try {
            st.execute(
                    "delete from customer where CustomerId=" +
                            customersView.getSelectionModel().getSelectedItem().getCustomerId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void editCustomer(TableColumn.CellEditEvent<Customer, String> customerStringCellEditEvent) {
        try {
            Customer selected=customersView.getSelectionModel().getSelectedItem();
            st.execute(
                    "update customer set CustomerName=\""+customerStringCellEditEvent.getNewValue()+"\""+
                            " where CustomerId=" + selected.getCustomerId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void editDeviceName(TableColumn.CellEditEvent cellEditEvent) {
        try {
            Device selected=devicesView.getSelectionModel().getSelectedItem();
            st.execute(
                    "update device set DeviceName=\""+cellEditEvent.getNewValue()+"\""+
                            " where DeviceId=" + selected.getDeviceId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void editDevicePrice(TableColumn.CellEditEvent<Device,Float> deviceFloatCellEditEvent) {
        try {
            Device selected=devicesView.getSelectionModel().getSelectedItem();
            selected.setPrice(selected.getPrice());
            st.execute(
                    "update device set Price=\""+deviceFloatCellEditEvent.getNewValue()+"\""+
                            " where DeviceId=" + selected.getDeviceId());
        } catch (SQLException | IllegalArgumentException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void editDeviceWeight(TableColumn.CellEditEvent<Device,Float> deviceFloatCellEditEvent) {
        try {
            Device selected=devicesView.getSelectionModel().getSelectedItem();
            st.execute(
                    "update device set Weight=\""+deviceFloatCellEditEvent.getNewValue()+"\""+
                            " where DeviceId=" + selected.getDeviceId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void editDeviceDescription(TableColumn.CellEditEvent<Device, String> deviceStringCellEditEvent) {
        try {
            Device selected=devicesView.getSelectionModel().getSelectedItem();
            st.execute(
                    "update device set Description=\""+deviceStringCellEditEvent.getNewValue()+"\""+
                            " where DeviceId=" + selected.getDeviceId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void addDeviceButtonClick(ActionEvent actionEvent) {
        try {
            st.execute("insert into device values(null, \"<name>\",2,2,\"<description>\")");
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void removeDeviceButtonClick(ActionEvent actionEvent) {
        try {
            st.execute(
                    "delete from device where DeviceId=" +
                            devicesView.getSelectionModel().getSelectedItem().getDeviceId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void editCustomerId(TableColumn.CellEditEvent cellEditEvent) {
        try {
            Product selected=productsView.getSelectionModel().getSelectedItem();
            st.execute(
                    "update product set CustomerId="+cellEditEvent.getNewValue()+
                            " where ProductId=" + selected.getProductId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void editDeviceId(TableColumn.CellEditEvent cellEditEvent) {
        try {
            Product selected=productsView.getSelectionModel().getSelectedItem();
            (new Product()).setDeviceId(selected.getDeviceId());
            st.execute(
                    "update product set DeviceId="+cellEditEvent.getNewValue()+
                            " where ProductId=" + selected.getProductId());
        } catch (SQLException | InvalidFieldValueException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void addProductButtonClick(ActionEvent actionEvent) {
        try {
            st.execute("insert into product values (null, (select min(DeviceId) from device), null)");
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }

    public void removeProductButtonClick(ActionEvent actionEvent) {
        try {
            st.execute(
                    "delete from product where ProductId=" +
                            productsView.getSelectionModel().getSelectedItem().getProductId());
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        load();
    }
}
