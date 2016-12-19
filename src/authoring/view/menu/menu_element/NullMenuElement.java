package authoring.view.menu.menu_element;

import authoring.view.menu.AbstractGameMenuElement;

/**
 * Provides a null implementation of AbstractGameMenuElement to default to when errors are encountered.
 *
 * @author Will Long
 * @version 12/19/16
 */
public class NullMenuElement extends AbstractGameMenuElement {
    public NullMenuElement() {
        super(null, null);
    }

    @Override
    protected void setFunctionality() {
        // nothing
    }
}
