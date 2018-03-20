import java.sql.SQLException;;

public class Register {
	
	private static Query query = new Query();

    private static void RegisterAparatus(String name, String description) {
        String sql = String.format("INSERT INTO Aparatus(AparatusName, WorkoutDescription) VALUES('%s', '%s')", name, description);
        try {
            Query.executeUpdate(sql);
        		
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void RegisterActivity(String name, String description) {
    		String sql = String.format("INSERT INTO Activities(ActivityName, ActivityDescription) VALUES('%s', '%s')", name, description);
    		try {
    			Query.executeUpdate(sql);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    }
    
    private static void RegisterActivity(String name, int aparatusID, String description) {
    		String sql = String.format("INSERT INTO Activities(ActivityName, ActivityDescription, AparatusID) VALUES('%s', '%s', %d)", name, description, aparatusID);
    		try {
    			Query.executeUpdate(sql);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    
    }
    
    private static void RegisterWorkout(int userID, int duration, int fitness, int workoutRating, String workoutNote, int centerID) {
    		String sql = String.format("INSERT INTO WorkOut(UserID, DurationMinutes, Fitness, WorkoutRating, WorkoutNote, CenterID) VALUES(%d, %d, %d, %d, '%s', %d)", userID, duration, fitness, workoutRating, workoutNote, centerID);
    		try {
    			Query.executeUpdate(sql);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    }
    
    

    public static void main(String[] args) {
    		// Register.RegisterAparatus("du", "heidas");
    }

}
