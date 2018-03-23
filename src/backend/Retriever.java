package backend;

import java.sql.*;

public class Retriever {
	
	public static Query query = new Query();
	
	public static ResultSet RetrieveWorkouts(int n, int userID) {
		String sql = String.format("SELECT * FROM WorkOut WHERE UserID=%d LIMIT %d", userID, n);
		return Query.execute(sql);
	}
	
	public static ResultSet RetrieveWorkouts(int userID, Timestamp start, Timestamp stop) {
		String sql = String.format("SELECT * FROM Workout WHERE TimeStamp>%t AND TimeStamp<%t AND UserID=%d", start, stop, userID);
		return Query.execute(sql);
	}
	
	public static ResultSet RetrieveAparatuses() {
		String sql = String.format("SELECT * FROM Aparatus");
		return Query.execute(sql);
	}
	
	public static ResultSet RetrieveActivites(String groupName) {
		String sql = String.format("SELECT ActivityID FROM ActivityGroups WHERE ActivityGroup='%s'", groupName);
		return Query.execute(sql);
	}
	
	public static ResultSet RetrieveCenterNameFromID(int id) {
		String sql = String.format("SELECT Name FROM Center WHERE CenterID=%d", id);
		return Query.execute(sql);
	}
	
	public static String retrieveCenterNameString(int id) throws SQLException {
		ResultSet rs = RetrieveCenterNameFromID(id);
		while (rs.next()) {
			String centerName = rs.getString("Name");
			return centerName;
		}
		return "";
	}

}
