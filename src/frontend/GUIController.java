package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import backend.Register;

public class GUIController {
	
	@FXML TextField apparatusNameInput;
	@FXML TextArea apparatusDescInput;
	@FXML Label apparatusRegisterLabel;
	/*
	@FXML TextField 
	@FXML TextField*/
	
	Register register;
	
	public void registerApparatus() {
		String apName = apparatusNameInput.getText();
		String apDesc = apparatusDescInput.getText();
		if ( apName == "" || apDesc == "") {
			apparatusRegisterLabel.setText("Invalid input");
			System.out.println("Invalid input");
		} else {
			Register.RegisterAparatus(apName, apDesc);
			System.out.println("aparatus registered");
		}
	}

	//TODO: Implementere metoder for � kontrollere GUIen

}
