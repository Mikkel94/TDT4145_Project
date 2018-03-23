package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import backend.*;

public class GUIController {
	
	
	// Register Apparatus
	@FXML TextField apparatusNameInput;
	@FXML TextArea apparatusDescInput;
	@FXML Label apparatusRegisterLabel;
	
	// register Activity
	@FXML TextField activityNameInput;
	@FXML TextArea activityDescInput;
	@FXML TextField appNameIn;
	@FXML Label actLabel;
	
	Register register = new Register();
	Retriever retriever = new Retriever();

	// register workout
	@FXML TextField workoutUserIDInput;
	@FXML TextField durationInput;
	@FXML TextField workoutCenterIDInput;
	@FXML TextArea workoutNoteInput;
	@FXML private ChoiceBox<Integer> fitnessLevelChoicebox;
	@FXML private ChoiceBox<Integer> workoutRatingChoicebox;
	@FXML Label workoutRegisterLabel;
	
	// find center name
	@FXML TextField findCenterNameCenterIDInput;
	@FXML TextArea centerNameOutput;
	@FXML Label findCenterNameLabel;
	
	// get n last workouts
	@FXML TextField viewnlastUserIDInput;
	@FXML TextField nLastWorkoutsInput;
	@FXML TextArea nLastWorkoutsOutput;
	
	// get timeperiod workouts
	@FXML TextField viewTimeperiodUserIDInput;
	@FXML TextField viewTimeperiodFromInput;
	@FXML TextField viewTimeperiodToInput;
	@FXML TextArea viewTimeperiodWorkoutsOutput;

	// create activity group
	@FXML TextField activityGroupsNameInput;
	@FXML TextField activityGroupsActivityIDInput;
	@FXML Label createActivityGroupLabel;
	@FXML TextArea activityIDToNameOutput;
	
	// get acitivty group
	@FXML TextField activityGroupsNameSearch;
	@FXML TextArea activityGroupsOutput;
	
	ObservableList<Integer> onetoten = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
	
	public void initialize() {
		fitnessLevelChoicebox.setValue(1);
		fitnessLevelChoicebox.setItems(onetoten);
		workoutRatingChoicebox.setValue(1);
		workoutRatingChoicebox.setItems(onetoten);
	}

	
	public void registerApparatus() {
		String apName = apparatusNameInput.getText();
		String apDesc = apparatusDescInput.getText();
		if ( apName.isEmpty() || apDesc.isEmpty()) {
			apparatusRegisterLabel.setText("Invalid input");
		} else {
			Register.RegisterAparatus(apName, apDesc);
			System.out.println("aparatus registered");
			apparatusRegisterLabel.setText("Registered");
			apparatusNameInput.setText("");
			apparatusDescInput.setText("");
		}
	}

	
	public boolean isStringInteger(String string) {
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (! Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	public void registerWorkout() {
		String userIDString = workoutUserIDInput.getText();
		String durationString = durationInput.getText();
		String centerIDString = workoutCenterIDInput.getText();
		if (! isStringInteger(userIDString) || ! isStringInteger(durationString) || ! isStringInteger(centerIDString)) {
			workoutRegisterLabel.setText("Invalid input");
		} else if (userIDString.isEmpty() || durationString.isEmpty() || centerIDString.isEmpty()) {
			workoutRegisterLabel.setText("Invalid input");
		} else {
			int userID = Integer.parseInt(userIDString);
			int duration = Integer.parseInt(durationString);
			int centerID = Integer.parseInt(centerIDString);
			String workoutNote = workoutNoteInput.getText();
			int fitness = fitnessLevelChoicebox.getValue();
			int workoutRating = workoutRatingChoicebox.getValue();
			Register.RegisterWorkout(userID, duration, fitness, workoutRating, workoutNote, centerID);
			workoutRegisterLabel.setText("Registered");
			workoutUserIDInput.setText("");
			durationInput.setText("");
			workoutCenterIDInput.setText("");
			workoutNoteInput.setText("");
		}
	}
	
	public void getCenterName() throws SQLException {
		String centerIDString = findCenterNameCenterIDInput.getText();
		if (! isStringInteger(centerIDString)) {
			findCenterNameLabel.setText("Not a valid ID");
		} else if (centerIDString.isEmpty()) {
			
		} else {
			int centerID = Integer.parseInt(centerIDString);
			String centerName = Retriever.retrieveCenterNameString(centerID);
			centerNameOutput.setText(centerName);
			findCenterNameCenterIDInput.setText("");
		}
	}

	
	public void registerActivity() {
		ArrayList<AparatusType> aparatuses = new ArrayList<>();
		ResultSet rs = Retriever.RetrieveAparatuses();
		try {
			while (rs.next()) {
				aparatuses.add(new AparatusType(rs.getString("AparatusName"), rs.getString("Workoutdescription"), rs.getInt("AparatusID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String apName = appNameIn.getText();
		String actName = activityNameInput.getText();
		String actDesc = activityDescInput.getText();
		if (apName.isEmpty() && !actDesc.isEmpty() && !actName.isEmpty()) {
			Register.RegisterActivity(actName, actDesc);
			appNameIn.setText("");
			activityNameInput.setText("");
			activityDescInput.setText("");
			actLabel.setText("Registered");
		} else if (!apName.isEmpty() && !actDesc.isEmpty() && !actName.isEmpty()) {
			for (AparatusType at : aparatuses) {
				if (apName.equals(at.getName())) {
					Register.RegisterActivity(actName, at.getId(), actDesc);
					appNameIn.setText("");
					activityNameInput.setText("");
					activityDescInput.setText("");
					actLabel.setText("Registered");
					break;
				}
			}
		} else {
			actLabel.setText("Invalid input");
		}
	}
	
	public void getNLastWorkouts() {
		System.out.println(nLastWorkoutsInput.getText());
		System.out.println(viewnlastUserIDInput.getText());
		int n = Integer.parseInt(nLastWorkoutsInput.getText());
		int id = Integer.parseInt(viewnlastUserIDInput.getText());
		
		String finalString = "";
		
		ResultSet rs = Retriever.RetrieveWorkouts(n, id);
		try {
			while (rs.next()) {
				finalString += String.format("WorkoutID: %d, UserID: %d, Duration in minutes: %d, Fitness: %d, WorkoutRating: %d, Workoutnote: %s, CenterID: %d \n",
						rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
			}
			nLastWorkoutsOutput.setText(finalString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getWorkoutsInIntervals() {
		int id = Integer.parseInt(viewTimeperiodUserIDInput.getText());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String tsString1 = viewTimeperiodFromInput.getText() + "00:00:00";
		String tsString2 = viewTimeperiodToInput.getText() + "00:00:00";
	    Date parsedDate1;
	    Date parsedDate2;
	    Timestamp timestamp1 = null;
	    Timestamp timestamp2 = null;
		try {
			parsedDate1 = (Date) dateFormat.parse(tsString1);
			parsedDate2 = (Date) dateFormat.parse(tsString2);
			timestamp1 = new Timestamp(parsedDate1.getTime());
			timestamp2 = new Timestamp(parsedDate2.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ResultSet rs = Retriever.RetrieveWorkouts(id, timestamp1, timestamp2);
		String finalString = "";
		try {
			while (rs.next()) {
				finalString += String.format("WorkoutID: %d, UserID: %d, Duration in minutes: %d, Fitness: %d, WorkoutRating: %d, Workoutnote: %s, CenterID: %d \n",
						rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
			
			}
			nLastWorkoutsOutput.setText(finalString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void createActivityGroup() {
		String acGrName = activityGroupsNameInput.getText();
		String acIDString = activityGroupsActivityIDInput.getText();
		if (! isStringInteger(acIDString)) {
			createActivityGroupLabel.setText("Invalid activity ID");
		} else if ( acGrName.isEmpty() || acIDString.isEmpty()) {
			createActivityGroupLabel.setText("Invalid input");
		} else {
			int acID = Integer.parseInt(acIDString);
			Register.RegisterActivityToGroup(acID, acGrName);
			createActivityGroupLabel.setText("Registered");
			System.out.println("activity registered");
			activityGroupsNameInput.setText("");
			activityGroupsActivityIDInput.setText("");
		}
	}
	
	public void getActivityGroup() throws SQLException {
		String activityGroupName = activityGroupsNameSearch.getText();
		String activitiesInGroup = Retriever.retrieveActivitiesString(activityGroupName);
		activityGroupsOutput.setText(activitiesInGroup);
		activityGroupsNameSearch.setText("");
	}
	
	public void getRegisteredActivities() throws SQLException {
		String allActivities = Retriever.getRegisteredActivities();
		activityIDToNameOutput.setText(allActivities);
	}
/*
	public void getNWorkouts()


	// henter nlast workouts og putter de i texfelt i GUI
	public void getNLastWorkouts()
	{
		//må kobles til GUI
		int n = nInput.getInt();
		int userID = UserIdInput.getInt();

		ResultSet rs = backend.retiveWorkout(n, userID);
		Sting output = querrytxt(rs);
		viewNLastLable.setText(output);
	}

	// Samme som over mend Aktiviteter som er gruppert
	public void getFindActivityGroups()
	{
		//må kobles til GUI
		String groupName = groupNameInput.getString();

		ResultSet rs = backend.RetrieveActivites(groupName);
		String output = querrytxt(rs);
		ActivetiesInGroups.setText(output);
	}
	// Tar et result set og produserer en printbar streng. 
	private String querrytxt(ResultSet rs)
	{
		String output = "";
		ResultSetMetaData rsmeta = rs.getMetaData();
		int n = rsmeta.getColumnCount();
		int i = 1;
		while (rs.next())
		{
			output += intToString(i) + ": ";
			for(i = 1; i <=n; i++)
			{
				output += rsmd.getColumnName(i) + ": " + fs.getsting(i) + ", "; ;
			}
			output += "/n";
		}
		return output;
	}
	}*/

}
