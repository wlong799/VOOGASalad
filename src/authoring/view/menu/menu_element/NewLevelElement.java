package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.ui.DialogFactory;
import authoring.view.menu.AbstractGameMenuElement;
import authoring.view.menu.GameAdder;

/**
 * Adds a new level to the game.
 *
 * @author Will Long, Bill Yu
 * @version 11/28/16
 */
public class NewLevelElement extends AbstractGameMenuElement {
    private static final String MENU_NAME = "New Level";

    private NewLevelElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        GameAdder adder = new GameAdder();
        myMenuItem.setOnAction(event -> {
            if (adder.addLevel(myController.getEnvironment())) {
                myController.getCanvasController().refresh();
            } else {
                showDuplicateIDError();
            }
        });
    }

    private void showDuplicateIDError() {
        DialogFactory.showErrorDialog(
                "Error",
                "Error adding level",
                "The ID you input is already used by another level"
        );
    }
}
