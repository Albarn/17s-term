package sample.collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.Customer;
import sample.models.InvalidFieldValueException;

import java.sql.*;

//mysql repository implementation
public class MySqlCustomerRepository implements Repository<Customer> {

    //jdbc statement for sql queries
    private Statement st;

    //initialize statement with connection to database
    public MySqlCustomerRepository() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String JDBC_URL = "jdbc:mysql://localhost:3306/audio_systems?user=root&password=2501&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(JDBC_URL);
        st = connection.createStatement();
    }

    @Override
    //get all customers
    public ObservableList<Customer> getAll() throws SQLException, InvalidFieldValueException {
        ObservableList<Customer> data = FXCollections.observableArrayList();

        //we select all rows from table
        ResultSet rs = st.executeQuery("select * from Customer");
        while (rs.next()) {
            //and convert it to customer instance
            Customer row = new Customer();
            row.setCustomerId(rs.getInt("CustomerId"));
            row.setCustomerName(rs.getString("CustomerName"));

            //then add it to collection
            data.add(row);
        }
        return data;
    }

    @Override
    //update customer
    public void update(Customer item) throws SQLException {
        //update customer sql statement
        st.execute(
                "update customer set CustomerName='" + item.getCustomerName() + "'" +
                        " where CustomerId=" + item.getCustomerId());
    }

    @Override
    //insert customer into db table
    public int insert(Customer item) throws SQLException {
        //insert statement
        st.execute(
                "insert into customer values(null, '" +
                        item.getCustomerName() + "')");

        //return inserted id
        ResultSet resultSet = st.executeQuery("select last_insert_id()");
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    //delete customer from table
    public void delete(Customer item) throws SQLException {
        //delete query
        st.execute(
                "delete from customer where CustomerId=" +
                        item.getCustomerId());
    }
}
