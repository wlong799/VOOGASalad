package authoring.view.menu.menu_element;

import java.io.File;
import java.io.IOException;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.stage.FileChooser;
import serializing.Marshaller;

public class SaveElement extends AbstractGameMenuElement {
	
	private FileChooser myFileChooser;
	private static final String MENU_NAME = "Save Game";

    private SaveElement(AuthoringController controller) {
        super(MENU_NAME, controller);
        myFileChooser = new FileChooser();
    }

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(e -> {
			myFileChooser.setTitle("Save Game");
			File saveFile = myFileChooser.showSaveDialog(null);
			if (saveFile != null) {
				String path = saveFile.toURI().toString();
				try {
					Marshaller.saveGame(myController.getEnvironment().getCurrentGame(), path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
