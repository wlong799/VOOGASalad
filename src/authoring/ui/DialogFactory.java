package authoring.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author billyu
 * show error and prompt dialogs
 */
public class DialogFactory {

	public static void showErrorDialog(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);

    	alert.showAndWait();
	}
	
}
