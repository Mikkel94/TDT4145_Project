import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    public static Connection conn;

    /**
     * Connection connect
     * 
     * Connects to the database.
     */


    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/WorkOutDiary?useSSL=false"; // For dev on localhost
            conn = DriverManager.getConnection(url, "root", "car22");


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

}
