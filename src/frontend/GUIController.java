package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.sql.ResultSet;
import java.sql.SQLException;
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

	@FXML TextField workoutUserIDInput;
	@FXML TextField durationInput;
	@FXML TextField workoutCenterIDInput;
	@FXML TextArea workoutNoteInput;
	@FXML private ChoiceBox<Integer> fitnessLevelChoicebox;
	@FXML private ChoiceBox<Integer> workoutRatingChoicebox;
	@FXML Label workoutRegisterLabel;
	
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
		} else if (!apName.isEmpty() && !actDesc.isEmpty() && !actName.isEmpty()) {
			for (AparatusType at : aparatuses) {
				if (apName.equals(at.getName())) {
					Register.RegisterActivity(actName, at.getId(), actDesc);
				}
				break;
			}
		} else {
			actLabel.setText("Invalid input");
		}
	}
	

	public void getNWorkouts()
	{
		int n = nInput.getInt();
		int userID = UserIdInput.getInt();
		ResultSet rs = backend.retiveWorkout(n, userID);
		Sting output = querrytxt(rs);
		viewNLastLable.setText(output);
	}

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
	}

	//TODO: Implementere metoder for � kontrollere GUIen

}
