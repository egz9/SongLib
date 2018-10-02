//Eric Zimmerman & Allen Guo
package View;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Song;
import application.SongLib;

public class Controller {
	
	@FXML ListView<Song> listArea;
	
	@FXML Button addB;
	@FXML Button editB;
	@FXML Button delB;
	@FXML Button saveB;
	@FXML Button cnclB;
	
	@FXML TextField songField;
	@FXML TextField artistField;
	@FXML TextField albumField;
	@FXML TextField yearField;
	
	/* 
	 * 
	 */

	ObservableList<Song> songList = SongLib.createlist();
	int add = 0;
	int delete = 0;
	int edit = 0;

	
	public void start(){
		//ObservableList<Song> songList = SongLib.createlist();
		
		System.out.println("-------------------");
		SongLib.sortSongList(songList);
		try {
			SongLib.add2SongList(songList, new Song("Light my Fire", "The Doors", "The Doors", "1967"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < songList.size(); i++){
			System.out.println(songList.get(i).toFullString());
		}
		listArea.setItems(songList);
		listArea.getSelectionModel().selectedIndexProperty().addListener
			((songList, oldVal, newVal) -> showDetails(listArea.getSelectionModel().getSelectedItem()));
		listArea.getSelectionModel().selectFirst();	
	}
	
	
	
	public void addButton(ActionEvent e){
		//disable input anywhere but detail display
		disableAllButDD(true);
		
		//clear text in each box, so the user knows they can change it
		songField.clear();
		artistField.clear();
		albumField.clear();
		yearField.clear();
		
		//set each detail as editable so user can add new info
		allowDetailEdits(true);
		
		//save button pops up. It will be the only button besides cancel
		//that the user can push
		add = 1;
		saveB.setVisible(true);
		cnclB.setVisible(true);
	}
	
	public void deleteButton(){
		Song selectedSong = listArea.getSelectionModel().getSelectedItem();
		System.out.println("DEL: " + selectedSong );
		try {
			SongLib.delFromSongList((ObservableList<Song>)listArea.getItems(), selectedSong);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* allows text fields to be changed. once user saves changes, the fields
	 * will no longer allow changes
	 */
	public void editButton(ActionEvent e){
		
		disableAllButDD(true); 	//disable input everywhere but detail display
		allowDetailEdits(true); //set each detail as editable so user can add new info
		saveB.setVisible(true);
		cnclB.setVisible(true);
		edit = 1;
	}
	
	//method runs when save button is pressed
	public void saveButton() throws IOException{
		//print some info to ensure save button is working correctly
		System.out.println("dog");
		System.out.println(songField.getText() + "_" + artistField.getText() + "_"
				+ albumField.getText() + "_" + yearField.getText());
		
		
		if(add == 1)
		{
			Song s1 = new Song(songField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
			/*
			for(Song s : songList) 
			{
				if (s.compareTo(s1) == 0)
				{
					showErrorBox();
					add = 0;
					return;
				}
			}
			*/
			if(SongLib.add2SongList(songList, s1))
			{
				SongLib.add2SongList(songList, s1);
				add = 0;
			}
			else
			{
				showErrorBox();
				add = 0;
				return;
			}
			/*songList.add(s1);
			SongLib.sortSongList(songList);
			
			FileWriter writer = new FileWriter("src/application/SavedLibrary.txt", true);
			writer.append(s1.toString() + "\n");
			writer.close();
			
			add = 0;
			*/
		}
		
		if(edit == 1)
		{
			
			Song selectedSong = listArea.getSelectionModel().getSelectedItem();
			try {
				SongLib.delFromSongList((ObservableList<Song>)listArea.getItems(), selectedSong);
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Song s2 = new Song(songField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
			if(SongLib.add2SongList(songList, s2))
			{
				SongLib.add2SongList(songList, s2);
				edit = 0;
			}
			else
			{
				showErrorBox();
				edit = 0;
				return;
			}
		}
		
		
		//dumb code, just for testing
		//String s = new String(songField.getText());
		//s.trim();
		//if (s.equals("error")){
		//	showErrorBox();
		//}
		
		
		disableAllButDD(false);
		allowDetailEdits(false);
		saveB.setVisible(false);
		cnclB.setVisible(false);
	}
	
	public void cancelButton(){
		
		disableAllButDD(false); 
		allowDetailEdits(false);
		saveB.setVisible(false);
		cnclB.setVisible(false);
		
		//not sure if I'm going to keep this
		songField.clear();
		artistField.clear();
		albumField.clear();
		yearField.clear();
	}
	
	/*
	 * pops up an error box when song is already in library
	 */
	public void showErrorBox(){
		/*
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(application.SongLib.pStage);
		VBox dVBox = new VBox(25);
		Text errorText = new Text ("Error: Song already in library");
		dVBox.getChildren().add(errorText);
		
		Scene dScene = new Scene(dVBox, 200, 75);
		dialog.setScene(dScene);
		dialog.show();
		*/
		Alert alert = new Alert(AlertType.ERROR,"Error: Song already in library");
		alert.showAndWait();
		
		if(alert.getResult() == ButtonType.OK) {
			alert.close();
		}
		
		disableAllButDD(false); 
		allowDetailEdits(false);
		saveB.setVisible(false);
		cnclB.setVisible(false);
	}
	
	
	//if b = true: song, artist, album and year text fields can
	//be edited by user. if b = false: the text fields can not be
	//edited by user
	private void allowDetailEdits(boolean b){
		songField.setEditable(b);
		artistField.setEditable(b);
		albumField.setEditable(b);
		yearField.setEditable(b);
	}
	
	//if b = true: disable input everywhere but detail display
	//if b = false: re-enable input everywhere
	private void disableAllButDD(boolean b){
		listArea.setDisable(b);
		addB.setDisable(b);
		editB.setDisable(b);
		delB.setDisable(b);
	}
	
	private void showDetails(Song s){
		songField.setText(s.getName());
		artistField.setText(s.getArtist());
		albumField.setText(s.getAlbum());
		yearField.setText(s.getYear());
	}
	
}
