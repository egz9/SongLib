package View;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
	
	@FXML Button editB;
	@FXML TextField songField;
	@FXML TextField artistField;
	@FXML TextField albumField;
	@FXML TextField yearField;
	
	/* allows text fields to be changed. once user saves changes, the fields
	 * will no longer allow changes
	 */
	public void editButton(ActionEvent e){
		songField.setEditable(true);
		artistField.setEditable(true);
		albumField.setEditable(true);
		yearField.setEditable(true);
		
	}

}
