package backend;

import java.sql.SQLException;;

public class Register {
	
	public static Query query = new Query();

    public static void RegisterAparatus(String name, String description) {
        String sql = String.format("INSERT INTO Aparatus(AparatusName, WorkoutDescription) VALUES('%s', '%s')", name, description);
        Query.update(sql);
    }
    
    public static void RegisterActivity(String name, String description) {
    		String sql = String.format("INSERT INTO Activities(ActivityName, ActivityDescription) VALUES('%s', '%s')", name, description);
    		Query.update(sql);
    }
    
    public static void RegisterActivity(String name, int aparatusID, String description) {
    		String sql = String.format("INSERT INTO Activities(ActivityName, ActivityDescription, AparatusID) VALUES('%s', '%s', %d)", name, description, aparatusID);
    		Query.update(sql);
    
    }
    
    public static void RegisterWorkout(int userID, int duration, int fitness, int workoutRating, String workoutNote, int centerID) {
    		String sql = String.format("INSERT INTO WorkOut(UserID, DurationMinutes, Fitness, WorkoutRating, WorkoutNote, CenterID) VALUES(%d, %d, %d, %d, '%s', %d)", userID, duration, fitness, workoutRating, workoutNote, centerID);
    		Query.update(sql);
    }
    
    public static void RegisterActivityToGroup(int activityID, String groupName) {
    		String sql = String.format("INSERT INTO ActivityGroups(ActivityID, ActivityGroup) VALUES(%d, '%s')", activityID, groupName);
    		Query.update(sql);
    }
}
