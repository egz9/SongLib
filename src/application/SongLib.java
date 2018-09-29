
package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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

	public static ArrayList<Song> createlist()
	{
		ArrayList<Song> songlist = new ArrayList<Song>();
		File file = new File("SavedLibrary.txt"); 
		Scanner sc = new Scanner(file);
		while (sc.hasNext()){
			String detail = sc.nextLine();
			String [] arrOfdet = detail.split("_");
			for(String a : arrOfdet) 
				System.out.println();
			int year = Integer.parseInt(arrOfdet[3]);
			Song s = new Song(arrOfdet[0],arrOfdet[1],arrOfdet[2], year);
			songlist.add(s);
		}
		sc.close();
		return songlist;
	}
}