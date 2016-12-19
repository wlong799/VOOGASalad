package authoring.view.menu.menu_element;

import authoring.view.menu.AbstractGameMenu;

/**
 * Provides a null implementation of AbstractGameMenu to default to in case of errors.
 *
 * @author Will Long
 * @version 12/19/16
 */
public class NullMenu extends AbstractGameMenu {

    public NullMenu() {
        super(null, null);
    }

    @Override
    protected void setFunctionality() {
        // nothing
    }
}
