package authoring.view.menu.menu_element;

import java.io.File;
import java.io.IOException;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.stage.FileChooser;

public class SaveElement extends AbstractGameMenuElement {
	
	private FileChooser fileChooser;
	private static final String MENU_NAME = "Save Game";

    private SaveElement(AuthoringController controller) {
        super(MENU_NAME, controller);
        fileChooser = new FileChooser();
    }

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(e -> {
			fileChooser.setTitle("Save Game");
			File saveFile = fileChooser.showSaveDialog(null);
			if (saveFile != null) {
				String path = saveFile.toURI().toString();
				try {
					myController.getMarshaller().saveGame(myController.getEnvironment().getCurrentGame(), path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
