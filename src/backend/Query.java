package backend;

import java.sql.*;

public class Query {

    public static PreparedStatement stmt;

    public Query() {
        DBConnector.connect();
    }

    private static ResultSet executeQuery(String sql) throws SQLException {
        stmt = DBConnector.conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    private static void executeUpdate(String sql) throws SQLException {
        stmt = DBConnector.conn.prepareStatement(sql);
        stmt.executeUpdate();
    }
    
    public static void update(String sql) {
    		try {
				executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    public static ResultSet execute(String sql) {
    		try {
				return executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		return null;
    }
}
