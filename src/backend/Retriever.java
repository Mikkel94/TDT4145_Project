package backend;

import java.sql.*;

public class Retriever {
	
	private Query query = new Query();
	
	private static ResultSet RetrieveWorkouts(int n, int userID) {
		String sql = String.format("SELECT * FROM WorkOut WHERE UserID=%d LIMIT %d", userID, n);
		try {
			return Query.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static ResultSet RetrieveWorkouts(int userID, Timestamp start, Timestamp stop) {
		String sql = String.format("SELECT * FROM Workout WHERE TimeStamp>%t AND TimeStamp<%t AND UserID=%d", start, stop, userID);
		try {
			return Query.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static ResultSet RetrieveAparatuses() {
		String sql = String.format("SELECT AparatusName, AparatusID FROM Aparatus");
		try {
			return Query.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
