package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.*;

import java.sql.*;

import sample.cdc.*;

public class Controller {
    private static Connection connObj;
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/cdc?user=root&password=2501&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public TableView<Teacher> teachersTable;
    public TableColumn<Teacher,Integer> teacherIdColumn;
    public TableColumn<Teacher,String> teacherFirstNameColumn;
    public TableColumn<Teacher,String> teacherLastNameColumn;

    public static void getDbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connObj = DriverManager.getConnection(JDBC_URL);
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
    public Controller(){
        getDbConnection();
    }

    public void loadDb(ActionEvent actionEvent) {
        try {
            stmt = connObj.createStatement();
            rs = stmt.executeQuery("SELECT * FROM teacher");
            ObservableList<Teacher> data;
            data = FXCollections.observableArrayList();
            /*
            try {
                rs = stmt.executeQuery("SELECT * FROM USER");
                while (rs.next()) {
                    Teacher row = new Teacher();

                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));

                    }
                    data.add(new Teacher());
                }
                tabla.setItems(data);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            */
            // Now do something with the ResultSet ....
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
