package ui.menu.menu_element;

import ui.AuthoringController;
import ui.menu.AbstractGameMenuElement;

/**
 * Adds a new level to the game
 *
 * @author Will Long
 * @version 11/17/16
 */
public class NewLevelElement extends AbstractGameMenuElement{
    private static final String MENU_NAME = "New Level";

    private NewLevelElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myController.getEnvironment();
        System.out.println("Creating new level...");
    }
}
