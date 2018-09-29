
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

//to handle user events we must write: implements EventHandler<ActionEvent> 
//whenever an event happens such as a button click the handle() method is called automatically
public class SongLib extends Application /*implements EventHandler<ActionEvent>*/ {
	
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/View/SongLib.fxml"));
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setTitle("Song Library");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	
	
}