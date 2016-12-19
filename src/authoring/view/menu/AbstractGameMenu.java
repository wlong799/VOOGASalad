package authoring.view.menu;

import authoring.AuthoringController;
import javafx.scene.control.Menu;

/**
 * AbstractGameMenu is both a container for AbstractGameMenuElements, as well as an AbstractGameMenuElement itself. This
 * allows it to gain the extra functionality of being a composite element, while still allowing it to be treated as a
 * generic AbstractGameMenuElement.
 *
 * @author Will Long
 * @version 12/18/16
 */
public abstract class AbstractGameMenu extends AbstractGameMenuElement {
    protected AbstractGameMenu(String menuName, AuthoringController controller) {
        super(menuName, controller);
    }

    /**
     * Adds a new AbstractGameMenuElement to the menu (possibly another composite AbstractGameMenu!).
     *
     * @param menuElement is menu element to add.
     */
    public void addGameMenuElement(AbstractGameMenuElement menuElement) {
        getMenu().getItems().add(menuElement.getMenuElement());
    }

    /**
     * Provides access to the menu element as a composite item, rather than just a generic menu item. Should only be
     * used in cases where the composite nature of AbstractGameMenu is required. Otherwise the more generic
     * getMenuElement() should be used. If myMenuItem is not a Menu for some reason, displays an error and returns null.
     *
     * @return the menu element, as a composite Menu rather than the normal MenuItem.
     */
    public Menu getMenu() {
        try {
            return (Menu) myMenuItem;
        } catch (ClassCastException e) {
            // TODO: 12/18/16 add error dialog
            return null;
        }
    }

    @Override
    protected void initMenuItem(String menuItemName) {
        myMenuItem = new Menu(menuItemName);
    }
}
