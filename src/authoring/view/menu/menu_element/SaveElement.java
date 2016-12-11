package authoring.view.menu.menu_element;

import java.io.File;
import java.io.IOException;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.stage.FileChooser;

public class SaveElement extends AbstractGameMenuElement {
	
	private FileChooser myFileChooser;

    private SaveElement(AuthoringController controller) {
        super(controller.getEnvironment().getLanguageResourceBundle().getString("save"), controller);
        myFileChooser = new FileChooser();
    }

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(e -> {
			myFileChooser.setTitle(myController.getEnvironment().getLanguageResourceBundle().getString("save"));
			File saveFile = myFileChooser.showSaveDialog(null);
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
