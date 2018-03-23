package backend;

import java.sql.*;

public class Retriever {
	
	public static Query query = new Query();
	public Retriever() {
        DBConnector.connect();
    }
	
	public static ResultSet RetrieveWorkouts(int n, int userID) {
		String sql = String.format("SELECT * FROM WorkOut WHERE UserID=%d LIMIT %d", userID, n);
		return Query.execute(sql);
	}
	
	public static ResultSet RetrieveWorkouts(int userID, Timestamp start, Timestamp stop) {
		String sql = String.format("SELECT * FROM Workout WHERE TimeStamp>? AND TimeStamp<? AND UserID=?");
		try {
			PreparedStatement stmt = DBConnector.conn.prepareStatement(sql);
			stmt.setTimestamp(1, start);
			stmt.setTimestamp(2, stop);
			stmt.setInt(3, userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Query.execute(sql);
	}
	
	public static ResultSet RetrieveAparatuses() {
		String sql = String.format("SELECT * FROM Aparatus");
		return Query.execute(sql);
	}
	
	public static String getRegisteredActivities() throws SQLException {
		String sql = String.format("SELECT * FROM activities;");
		ResultSet rs = Query.execute(sql);
		String returnString = "";
		while (rs.next()) {
			returnString += rs.getString("ActivityID") + ": " + rs.getString("ActivityName") + "\n";
		}
		return returnString;
	}
	
	public static String getRegisteredApparatuses() throws SQLException {
		String sql = String.format("SELECT * FROM aparatus;");
		ResultSet rs = Query.execute(sql);
		String returnString = "";
		while (rs.next()) {
			returnString += rs.getString("AparatusID") + ": " + rs.getString("AparatusName") + "\n";
		}
		return returnString;
	}
	
	public static ResultSet RetrieveActivites(String groupName) { 
		String sql = String.format("SELECT ActivityName FROM activities JOIN activitygroups ON activities.ActivityID = activitygroups.ActivityID WHERE ActivityGroup = '%s';", groupName);
		return Query.execute(sql);
	}
	
	public static String retrieveActivitiesString(String groupName) throws SQLException {
		ResultSet rs = RetrieveActivites(groupName);
		String returnString = "";
		while (rs.next()) {
			returnString += rs.getString("ActivityName") + "\n";
		}
		return returnString;
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
