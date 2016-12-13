package authoring.view.menu.menu_element;

import java.io.File;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import resources.ResourceBundles;

/**
 * @author rachelbransom
 *Menu item to change the canvas background
 */
public class ChangeLevelBackgroundElement extends AbstractGameMenuElement{

	private static final ResourceBundle inspectorProperties = ResourceBundles.inspectorProperties;
	private static final String MENU_NAME = inspectorProperties.getString("change");
	
	protected ChangeLevelBackgroundElement(AuthoringController controller) {
		super(MENU_NAME, controller);
	}

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(inspectorProperties.getString("choose"));
        	File imageFile = fileChooser.showOpenDialog(new Stage());
        	if (imageFile == null) return;
        	File dir = new File(inspectorProperties.getString("DATA_DIR"));
			String imagePath = imageFile.toURI().toString().substring(dir.toURI().toString().length());
			myController.getEnvironment().getCurrentLevel().getBackground().clearImagePaths();
			myController.getEnvironment().getCurrentLevel().getBackground().appendImagePath(imagePath);
			myController.getEnvironment().triggerUpdate();
		});
	}
}
