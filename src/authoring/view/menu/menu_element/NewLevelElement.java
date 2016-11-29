package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.LevelGenerator;

/**
 * Adds a new level to the game.
 *
 * @author Will Long
 * @version 11/17/16
 */
public class NewLevelElement extends AbstractGameMenuElement {
    private static final String MENU_NAME = "New Level";

    private NewLevelElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myMenuItem.setOnAction(event -> {
            myController.getEnvironment().addLevel(LevelGenerator.getTestLevelB());
            myController.getCanvasViewController().refresh();
        });
    }
}
