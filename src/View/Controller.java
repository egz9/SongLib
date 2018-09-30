package View;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
	
	@FXML ListView<String> listArea;
	
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
		saveB.setVisible(true);
		cnclB.setVisible(true);
	}
	
	/* allows text fields to be changed. once user saves changes, the fields
	 * will no longer allow changes
	 */
	public void editButton(ActionEvent e){
		
		disableAllButDD(true); 	//disable input everywhere but detail display
		allowDetailEdits(true); //set each detail as editable so user can add new info
		saveB.setVisible(true);
		cnclB.setVisible(true);
	}
	
	public void saveButton(){
		//print some info to ensure save button is working correctly
		System.out.println("dog");
		System.out.println(songField.getText() + "_" + artistField.getText() + "_"
				+ albumField.getText() + "_" + yearField.getText());
		
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
	
}
