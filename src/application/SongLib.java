
package application;

import java.io.File;
import java.io.InputStream;
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
		Scene scene = new Scene(root, 656, 504);
		primaryStage.setTitle("Song Library");
		primaryStage.setScene(scene);
		primaryStage.show();
		createlist();
	}

	public static ArrayList<Song> createlist()
	{	
		ArrayList<Song> songlist = new ArrayList<Song>();
		InputStream inFile = SongLib.class.getResourceAsStream("/application/SavedLibrary.txt");
		Scanner sc = new Scanner(inFile);
		while (sc.hasNext()){
			String detail = sc.nextLine();
			String [] arrOfdet = detail.split("_");
			for(String a : arrOfdet) 
				System.out.println(a);
			if(arrOfdet.length > 2 && arrOfdet[2] != null)
			{
				if(arrOfdet.length > 3 && arrOfdet[3] != null)
				{
					Song s = new Song(arrOfdet[0], arrOfdet[1], arrOfdet[2], arrOfdet[3]);
					songlist.add(s);
				}
				else {
					Song s1 = new Song(arrOfdet[0],arrOfdet[1],arrOfdet[2],"");
					songlist.add(s1);
				}
			}
			else {
				Song s2 = new Song(arrOfdet[0],arrOfdet[1],"","");
				songlist.add(s2);
			}
		}
		sc.close();
		return songlist;
	}
	/*
	public static AddSong()
	{
		
	//}
	}
	*/
}