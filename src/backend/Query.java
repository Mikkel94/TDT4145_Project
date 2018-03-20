package backend;

import java.sql.*;

public class Query {

    public static PreparedStatement stmt;

    public Query() {
        DBConnector.connect();
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        stmt = DBConnector.conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public static void executeUpdate(String sql) throws SQLException {
        stmt = DBConnector.conn.prepareStatement(sql);
        stmt.executeUpdate();
    }
}
