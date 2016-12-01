package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Provides simple divider element within a menu.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class SeparatorMenuElement extends AbstractGameMenuElement {

    protected SeparatorMenuElement(AuthoringController controller) {
        super(null, controller);
    }

    @Override
    protected void setFunctionality() {
        // nothing
    }

    @Override
    protected void initMenuItem(String menuItemName) {
        myMenuItem = new SeparatorMenuItem();
    }
}
