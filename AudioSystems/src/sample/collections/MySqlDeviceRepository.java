package sample.collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.Device;
import sample.models.InvalidFieldValueException;

import java.sql.*;

//mysql device repository implementation
public class MySqlDeviceRepository implements Repository<Device> {

    //statement for sql queries
    private Statement st;

    //initialize statement
    public MySqlDeviceRepository() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String JDBC_URL = "jdbc:mysql://localhost:3306/audio_systems?user=root&password=2501&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(JDBC_URL);
        st = connection.createStatement();
    }

    @Override
    //select devices from table
    public ObservableList<Device> getAll() throws SQLException, InvalidFieldValueException {

        //select rows and convert it to device instances
        ObservableList<Device> data = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery("select * from Device");
        while (rs.next()) {
            Device row = new Device();
            row.setDeviceId(rs.getInt("DeviceId"));
            row.setDeviceName(rs.getString("DeviceName"));
            row.setWeight(rs.getFloat("Weight"));
            row.setPrice(rs.getFloat("Price"));
            row.setDescription(rs.getString("Description"));
            data.add(row);
        }
        return data;
    }

    @Override
    //update device
    public void update(Device item) throws SQLException {
        st.execute(
                //update statement for device table
                "update device set DeviceName='" + item.getDeviceName() + "', " +
                        "Weight=" + item.getWeight() + ", " +
                        "Price=" + item.getPrice() + ", " +
                        "Description='" + item.getDescription() + "' " +
                        " where DeviceId=" + item.getDeviceId());
    }

    @Override
    //inset new device
    public int insert(Device item) throws SQLException {
        st.execute(
                //insert statement for device table
                "insert into device values(null, '" +
                        item.getDeviceName() + ",'" +
                        item.getPrice() + "," +
                        item.getWeight() + ",'" +
                        item.getDescription() + "')");
        ResultSet resultSet = st.executeQuery("select last_insert_id()");
        resultSet.next();
        //return inserted device id
        return resultSet.getInt(1);
    }

    @Override
    //delete device from table
    public void delete(Device item) throws SQLException {
        st.execute(
                //delete statement for device table
                "delete from Device where DeviceId=" +
                        item.getDeviceId());
    }
}
