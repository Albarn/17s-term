package sample.collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.InvalidFieldValueException;
import sample.models.Product;

import java.sql.*;

//mysql repository implementation
public class MySqlProductRepository implements Repository<Product> {

    //statement for sql queries
    private Statement st;

    //initialize statement
    public MySqlProductRepository() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String JDBC_URL = "jdbc:mysql://localhost:3306/audio_systems?user=root&password=2501&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(JDBC_URL);
        st = connection.createStatement();
    }

    @Override
    //select all products from db table
    public ObservableList<Product> getAll() throws SQLException, InvalidFieldValueException {
        ObservableList<Product> data = FXCollections.observableArrayList();

        //select query
        ResultSet rs = st.executeQuery("select * from Product");
        while (rs.next()) {
            //convert table row to product instance
            Product row = new Product();
            try {
                row.setCustomerId(rs.getInt("CustomerId"));
            } catch (Exception ignored) { }
            row.setProductId(rs.getInt("ProductId"));
            row.setDeviceId(rs.getInt("DeviceId"));
            //add it to collection
            data.add(row);
        }
        return data;
    }

    @Override
    //update product
    public void update(Product item) throws SQLException {
        //try to get customer id(it can be null)
        int customerId = -1;
        try {
            customerId = item.getCustomerId();
        } catch (Exception ignored) { }
        st.execute(
                "update product set CustomerId=" +
                        //if customer id is bad - set null
                        (customerId > 0 ? customerId : "null") +
                        ", DeviceId=" + item.getDeviceId() +
                        " where ProductId=" + item.getProductId());
    }

    @Override
    //insert product into table
    public int insert(Product item) throws SQLException {
        //try to get customer id
        int customerId = -1;
        try {
            customerId = item.getCustomerId();
        } catch (Exception ignored) {
        }

        //if customer id is bad, set null
        st.execute(
                "insert into product values(null, " +
                        item.getDeviceId() + "," + (customerId > 0 ? customerId : "null") + ")");
        ResultSet resultSet = st.executeQuery("select last_insert_id()");
        resultSet.next();
        //return inserted id
        return resultSet.getInt(1);
    }

    @Override
    //delete product from table
    public void delete(Product item) throws SQLException {
        //delete statement
        st.execute(
                "delete from product where ProductId=" +
                        item.getProductId());
    }
}
