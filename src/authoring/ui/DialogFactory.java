package authoring.ui;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import resources.ResourceBundles;

/**
 * @author billyu
 * show error and prompt dialogs
 */
public class DialogFactory {
	
	private static boolean shownSessionExpired = false;

	public static void showErrorDialog(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);

    	alert.showAndWait();
	}
	
	public static void showSessionExpired() {
		if (shownSessionExpired) return;
		ResourceBundle rs = ResourceBundles.languageProperties;
		showErrorDialog(rs.getString("expired"), 
				rs.getString("expiredTitle"),
				rs.getString("sucksForYou"));
		shownSessionExpired = true;
	}
	
}
