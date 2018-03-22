package backend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
	
	//Kj�r denne filen for � launche appen

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Treningsdagbok");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
