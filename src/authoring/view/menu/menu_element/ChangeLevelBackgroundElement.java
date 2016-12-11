package authoring.view.menu.menu_element;

import java.io.File;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author rachelbransom
 *Menu item to change the canvas background
 */
public class ChangeLevelBackgroundElement extends AbstractGameMenuElement{

	protected ChangeLevelBackgroundElement(AuthoringController controller) {
		super(controller.getEnvironment().getLanguageResourceBundle().getString("changeLevelBackground"), controller);
	}

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
        	File imageFile = fileChooser.showOpenDialog(new Stage());
        	if (imageFile == null) return;
			String imagePath = imageFile.toURI().toString();
			myController.getEnvironment().getCurrentLevel().getBackground().clearImagePaths();
			myController.getEnvironment().getCurrentLevel().getBackground().appendImagePath(imagePath);
		});
	}
}
