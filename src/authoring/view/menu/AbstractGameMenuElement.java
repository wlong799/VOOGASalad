package authoring.view.menu;


import authoring.AuthoringController;
import javafx.scene.control.MenuItem;

/**
 * Abstract superclass for all elements in the game menu, both terminal elements, and container menu elements. Different
 * implementations of AbstractGameMenuElement primarily provided through overriding the setFunctionality method.
 *
 * @author Will Long
 * @version 12/18/16
 */
public abstract class AbstractGameMenuElement {
    protected MenuItem myMenuItem;
    protected AuthoringController myController;

    /**
     * Creates a new menu element with the specified name and top-level controller.
     *
     * @param menuElementName is name of menu element.
     * @param controller      is top-level controller of authoring environment.
     */
    protected AbstractGameMenuElement(String menuElementName, AuthoringController controller) {
        myController = controller;
        initMenuItem(menuElementName);
        setFunctionality();
    }

    public MenuItem getMenuElement() {
        return myMenuItem;
    }

    /**
     * Creates the menu element. Placed outside of constructor code because sub-classes may need to instantiate menuItem
     * to something other than a simple MenuItem.
     *
     * @param menuItemName is name of menu element.
     */
    protected void initMenuItem(String menuItemName) {
        myMenuItem = new MenuItem(menuItemName);
    }

    /**
     * Overriding this method provides actual functionality to the menu element, specifying how it interacts with the
     * game.
     */
    protected abstract void setFunctionality();

}
