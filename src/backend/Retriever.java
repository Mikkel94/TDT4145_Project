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
	
	public static ResultSet RetrieveWorkouts(int userID, String start, String stop) {
		String sql = String.format("SELECT * FROM Workout WHERE TimeStamp>TIMESTAMP(\"%s\") AND TimeStamp<TIMESTAMP(\"%s\") AND UserID=%d", start, stop, userID);
		return Query.execute(sql);
	}
	
	public static String retrieveWorkoutsString(int userID, String start, String stop) throws SQLException {
		ResultSet rs = RetrieveWorkouts(userID,start,stop);
		String finalString = "";
		while (rs.next()) {
			finalString += String.format("WorkoutID: %d, UserID: %d, Duration in minutes: %d, Fitness: %d, WorkoutRating: %d, Workoutnote: %s, CenterID: %d \n",
					rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
		}
		return finalString;
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
