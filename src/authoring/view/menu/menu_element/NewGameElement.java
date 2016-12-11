package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import authoring.view.menu.GameAdder;

/**
 * Adds a new game to the workspace.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class NewGameElement extends AbstractGameMenuElement {
    private static final String MENU_NAME = "New Game";

    private NewGameElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
    	GameAdder adder = new GameAdder();
        myMenuItem.setOnAction(event -> {
        	adder.addGame(myController.getEnvironment());
        });
    }
}
