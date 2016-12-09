package authoring.view.menu.menu_element;

import java.io.File;
import java.io.IOException;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.core.Game;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class LoadElement extends AbstractGameMenuElement {
	
	private FileChooser myFileChooser;
	private static final String CHOOSER_TITLE = "Load Game File";
	private static final String MENU_NAME = "Load Game";

    private LoadElement(AuthoringController controller) {
        super(MENU_NAME, controller);
        myFileChooser = new FileChooser();
    }

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(e -> {
			myFileChooser.setTitle(CHOOSER_TITLE);
			myFileChooser.getExtensionFilters().addAll(new ExtensionFilter("xml files", "*.xml"));
			File gameFile = myFileChooser.showOpenDialog(null);
			if (gameFile != null) {
				String path = gameFile.toURI().toString();
				try {
					Game game = myController.getMarshaller().loadGame(path);
					myController.getEnvironment().addGame(game);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
