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
	
	@FXML ListView listArea;
	
	@FXML Button editB;
	@FXML Button delB;
	@FXML Button saveB;
	@FXML Button addB;
	
	@FXML TextField songField;
	@FXML TextField artistField;
	@FXML TextField albumField;
	@FXML TextField yearField;
	
	/*
	 * 
	 */
	public void addButton(ActionEvent e){
		//disable input anywhere but detail display
		listArea.setDisable(true);
		addB.setDisable(true);
		editB.setDisable(true);
		delB.setDisable(true);
		
		
		//clear text in each box, so the user knows they can change it
		songField.clear();
		artistField.clear();
		albumField.clear();
		yearField.clear();
		
		//set each detail as editable so user can add new info
		allowDetailEdits(true);
		
		//save button pops up, to let program know when user wants to save changes
		saveB.setVisible(true);
		
		saveB.setOnAction(event -> {
			System.out.println("dog");
			listArea.setDisable(false);
			editB.setDisable(false);
			delB.setDisable(false);
			addB.setDisable(false);
			allowDetailEdits(false);
			saveB.setVisible(false);
			return;
		});
	}
	/* allows text fields to be changed. once user saves changes, the fields
	 * will no longer allow changes
	 */
	public void editButton(ActionEvent e){
		//disable input everywhere but detail display
		listArea.setDisable(true);
		addB.setDisable(true);
		editB.setDisable(true);
		delB.setDisable(true);
		
		allowDetailEdits(true);
		saveB.setVisible(true);
	}
	
	public void allowDetailEdits(boolean b){
		songField.setEditable(b);
		artistField.setEditable(b);
		albumField.setEditable(b);
		yearField.setEditable(b);
	}
	
}
