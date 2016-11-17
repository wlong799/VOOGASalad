package ui.menu;


import javafx.scene.control.MenuItem;

/**
 * Abstract superclass for all GameMenuElements. GameMenuElements are placed in GameMenus within the GameMenuBar. These
 * elements are what provide the actual functionality within the GameMenuBar.
 *
 * @author Will Long
 * @version 11/17/16
 */
public abstract class AbstractGameMenuElement {
    private MenuItem myMenuItem;

    public AbstractGameMenuElement(String menuElementName) {
        myMenuItem = new MenuItem(menuElementName);
        setFunctionality();
    }

    public MenuItem getMenuElement() {
        return myMenuItem;
    }

    /**
     * Adds the actual implementation to the menu element, specifying how it interacts with the game.
     */
    protected abstract void setFunctionality();
}
