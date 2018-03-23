package backend;

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
            //String url = "jdbc:mysql://localhost:3306/WorkOutDiary?useSSL=false"; // For dev on localhost
            //conn = DriverManager.getConnection(url, "root", "car22");
            
            String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/mikkesa_tdt4145?useSSL=false";
            conn = DriverManager.getConnection(url, "mikkesa_db", "rullekake");
    			


        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

}
