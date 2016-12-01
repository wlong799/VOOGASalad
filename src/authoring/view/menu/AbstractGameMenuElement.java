package authoring.view.menu;


import authoring.AuthoringController;
import javafx.scene.control.MenuItem;

/**
 * Abstract superclass for all GameMenuElements. GameMenuElements are placed in GameMenus within the GameMenuBar. These
 * elements are what provide the actual functionality within the GameMenuBar.
 *
 * @author Will Long
 * @version 11/17/16
 */
public abstract class AbstractGameMenuElement {
    protected MenuItem myMenuItem;
    protected AuthoringController myController;

    protected AbstractGameMenuElement(String menuElementName, AuthoringController controller) {
        myController = controller;
        initMenuItem(menuElementName);
        setFunctionality();
    }

    public MenuItem getMenuElement() {
        return myMenuItem;
    }

    /**
     * Adds the actual implementation to the menu element, specifying how it interacts with the game.
     */
    protected abstract void setFunctionality();

    protected void initMenuItem(String menuItemName) {
        myMenuItem = new MenuItem(menuItemName);
    }
}
