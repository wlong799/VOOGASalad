// This entire file is part of my masterpiece.
// Will Long

/*
 * MASTERPIECE EXPLANATION:
 *
 * Just a simple concrete subclass of AbstractGameMenuElement to show how it works in practice. Polymorphism in menu
 * element functionality is achieved by overriding the protected setFunctionality() method. This method is called in
 * the AbstractGameMenuElement constructor, showing usage of the Template Method design pattern. AbstractGameMenuElement
 * provides the structure to its constructor, but its the subclasses that actually define how the sub-steps of the
 * constructor work.
 */

package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.level.Level;

/**
 * Deletes the current level.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class DeleteLevelElement extends AbstractGameMenuElement {

    private DeleteLevelElement(AuthoringController controller) {
        super(controller.getEnvironment().getLanguageResourceBundle().getString("DELETE_LEVEL"), controller);
    }

    /**
     * Calls on the authoring environment to delete the currently selected level.
     */
    @Override
    protected void setFunctionality() {
        myMenuItem.setOnAction(event -> {
            Level level = myController.getEnvironment().getCurrentLevel();
            myController.getEnvironment().removeLevel(level);
        });
    }
}
