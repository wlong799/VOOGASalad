// This entire file is part of my masterpiece.
// Will Long

/*
 * MASTERPIECE EXPLANATION:
 *
 * As mentioned in the GameMenuFactory masterpiece class, the use of abstract classes is critical for being able to
 * work with many different types of menu elements in a identical way. GameMenuFactory relies on using methods available
 * to the abstract superclasses. In addition to showing understanding of using inheritance and polymorphism, this class
 * shows usage of the Composite design pattern. The game menu can be thought of as a tree structure containing a bunch
 * of menu elements contained in menus. However, the menus themselves can also be elements themselves (i.e. menus can
 * contain other menus). By having AbstractGameMenu extend AbstractGameMenuElement, it is possible to treat both leaf
 * and branch nodes within the GameMenuView in the same manner, providing greater flexibility to the view.
 */
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
     * getMenuElement() should be used.
     *
     * @return the menu element, as a composite Menu rather than the normal MenuItem.
     */
    public Menu getMenu() {
        return (Menu) myMenuItem;
    }

    @Override
    protected void initMenuItem(String menuItemName) {
        myMenuItem = new Menu(menuItemName);
    }
}
