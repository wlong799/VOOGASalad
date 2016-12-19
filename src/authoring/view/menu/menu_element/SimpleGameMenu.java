package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenu;

/**
 * Provides basic implementation of a game menu. No functionality, simply provides a container with a name to put other
 * game menu elements into.
 *
 * @author Will Long
 * @version 12/18/16
 */
public class SimpleGameMenu extends AbstractGameMenu {
    private SimpleGameMenu(String menuName, AuthoringController controller) {
        super(menuName, controller);
    }

    @Override
    protected void setFunctionality() {
        // nothing
    }
}
