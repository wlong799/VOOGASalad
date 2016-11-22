package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;

/**
 * Adds a new level to the game
 *
 * @author Will Long
 * @version 11/17/16
 */
public class NewLevelElement extends AbstractGameMenuElement{
    private static final String MENU_NAME = "New Level";
    // TODO: link this to resources file

    private NewLevelElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myController.getEnvironment();
        System.out.println("Creating new level...");
    }
}
