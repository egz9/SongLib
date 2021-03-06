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

	ObservableList<Song> songList /*= SongLib.createlist()*/;
	int add = 0;
	int delete = 0;
	int edit = 0;

	
	public void start(){
		songList = SongLib.createlist();
		
		//System.out.println("-------------------");
		SongLib.sortSongList(songList);
		//System.out.println("SONGLIST");
		for(int i = 0; i < songList.size(); i++){
			//System.out.println(songList.get(i).toFullString());
		}
		listArea.setItems(songList);
		listArea.getSelectionModel().selectedIndexProperty().addListener
			((songList, oldVal, newVal) -> showDetails());
		
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
		if (listArea.getItems().size() < 1){
			Alert alert = new Alert(AlertType.ERROR,"Error: No songs in library to delete");
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK) {
				alert.close();
			}
			return;
		}
		Song selectedSong = listArea.getSelectionModel().getSelectedItem();
		int selectedIndex = listArea.getSelectionModel().getSelectedIndex();
		
		//ask user if they are sure they want to delete
		Alert alert = new Alert(AlertType.CONFIRMATION,"Are you sure you want to delete selected song");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.CANCEL) {
			return;
		}
		
		Song nextSongToSelect;
		if (listArea.getItems().size() <= 1) {
			nextSongToSelect = null;
		}
		//if its the last song in list, select previous song
		else if (selectedIndex + 1 >= listArea.getItems().size()){
			nextSongToSelect = listArea.getItems().get(selectedIndex - 1);
		}
		//otherwise select next song
		else {
			nextSongToSelect = listArea.getItems().get(selectedIndex + 1);
		}
		//System.out.println("DEL: " + selectedSong + " at index " + selectedIndex );
		try {
			SongLib.delFromSongList((ObservableList<Song>)listArea.getItems(), selectedSong);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (nextSongToSelect != null){
			//System.out.println("selecting " + nextSongToSelect.toFullString());
			listArea.getSelectionModel().select(nextSongToSelect);
			showDetails();
		}
		//last song was deleted so we clear textfields
		else {
			songField.clear();
			artistField.clear();
			albumField.clear();
			yearField.clear();
		}
	}

	/* allows text fields to be changed. once user saves changes, the fields
	 * will no longer allow changes
	 */
	public void editButton(ActionEvent e){
		
		if (listArea.getItems().size() < 1){
			Alert alert = new Alert(AlertType.ERROR,"Error: No song to edit");
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK) {
				alert.close();
			}
			return;
		}
		
		disableAllButDD(true); 	//disable input everywhere but detail display
		allowDetailEdits(true); //set each detail as editable so user can add new info
		saveB.setVisible(true);
		cnclB.setVisible(true);
		edit = 1;
		
	}
	
	//method runs when save button is pressed
	public void saveButton() throws IOException{
		//print some info to ensure save button is working correctly
		//System.out.println("dog");
		//System.out.println(songField.getText() + "_" + artistField.getText() + "_" + albumField.getText() + "_" + yearField.getText());
		if (!userInputCheck()) return;
			
		
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
				//SongLib.add2SongList(songList, s1);
				add = 0;
				listArea.getSelectionModel().select(s1);
			}
			else
			{
				showErrorBox();
				add = 0;
				showDetails();
			}
			/*songList.add(s1);
			SongLib.sortSongList(songList);
			
			FileWriter writer = new FileWriter("src/application/SavedLibrary.txt", true);
			writer.append(s1.toString() + "\n");
			writer.close();
			
			add = 0;
			*/
		}
		
		else if(edit == 1)
		{
			Song selectedSong = listArea.getSelectionModel().getSelectedItem();
			
			Song s2 = new Song(songField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
			for(Song s : songList)	
			{
				if((s.compareTo(s2) == 0 )|| (selectedSong.compareTo(s2) == 0))
				{
					showErrorBox();
					edit = 0;
					listArea.getSelectionModel().select(selectedSong);
					showDetails();
					disableAllButDD(false);
					allowDetailEdits(false);
					saveB.setVisible(false);
					cnclB.setVisible(false);
					return;
				}
			}
			
			songList.add(s2);
			SongLib.sortSongList(songList);
			try {
				SongLib.delFromSongList((ObservableList<Song>)listArea.getItems(), selectedSong);
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			edit = 0;
			listArea.getSelectionModel().select(s2);
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
		//songField.clear();
		//artistField.clear();
		//albumField.clear();
		//yearField.clear();
		showDetails();
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
	
	private void showDetails(){
		Song s = listArea.getSelectionModel().getSelectedItem();
		if (s == null){ 
			//System.out.println("Null song, cant display details");
			return;		
		}
		//System.out.println("[sd] " + s.toFullString());
		songField.setText(s.getName());
		artistField.setText(s.getArtist());
		albumField.setText(s.getAlbum());
		yearField.setText(s.getYear());
	}
	//if there are underscores in any of the text fields or if the year is not a number
	//information alerts pop up
	private boolean userInputCheck(){
		
		//no underscores
		if (songField.getText().contains("_") || artistField.getText().contains("_") 
		|| albumField.getText().contains("_")   || yearField.getText().contains("_")){
			Alert alert = new Alert(AlertType.INFORMATION,"No underscores please");
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK) alert.close();
			return false;
		}
		//year must be a number
		if (yearField.getText().length() >= 1){
			try {
				Integer.parseInt(yearField.getText());
			} catch (NumberFormatException | NullPointerException nfe){
				Alert alert = new Alert(AlertType.INFORMATION,"Year must be an integer");
				alert.showAndWait();
				if(alert.getResult() == ButtonType.OK) alert.close();
				return false;
			}
		}
		
		//name and artist cant be blank
		if (artistField.getText().length() < 1 || songField.getText().length() < 1){
			Alert alert = new Alert(AlertType.INFORMATION,"artist and song name cannot be blank");
			alert.showAndWait();
			if(alert.getResult() == ButtonType.OK) alert.close();
			return false;
		}
		
		return true;
	}
	
}
