//Eric Zimmerman & Allen Guo

package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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

	public static Stage pStage;
	@Override
	public void start(Stage primaryStage) throws Exception{
		pStage = primaryStage;
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/SongLib.fxml"));
		Scene scene = new Scene(root, 656, 504);
		primaryStage.setTitle("Song Library");
		primaryStage.setScene(scene);
		primaryStage.show();
		ArrayList<Song> songList = createlist();
		
		System.out.println("-------------------");
		sortSongList(songList);
		add2SongList(songList, new Song("Light my Fire", "The Doors", "The Doors", "1967"));
		for(int i = 0; i < songList.size(); i++){
			System.out.println(songList.get(i));
		}
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
	/* sorts song list using java's Collections.sort method and
	 * sortByNameArtist object allowing sort method to know
	 * exactly how to compare song objects
	 */
	public void sortSongList(ArrayList<Song> sl){
		Collections.sort(sl, new SortByNameArtist());
	}
	
	/*
	 * returns true or false based on whether or not the newSong
	 * was successfully added to list
	 */
	public boolean add2SongList(ArrayList<Song> sl, Song newSong)throws IOException{
		//check entire list to see if song has already been added
		for(Song s : sl ) if (s.compareTo(newSong) == 0) return false;
		
		sl.add(newSong); //add song to end of list
		sortSongList(sl);//sort list to put song in the right spot
		
		//add to saved library
		FileWriter writer = new FileWriter("src/application/SavedLibrary.txt", true);
		writer.append(newSong.toString() + "\n");
		writer.close();
		
		//file has successfully been added to list and file so return true
		return true;
	}
	
	
	/*
	public static AddSong()
	{
		
	//}
	}
	*/
}