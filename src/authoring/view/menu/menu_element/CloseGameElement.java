package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;

/**
 * Close currently active game.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class CloseGameElement extends AbstractGameMenuElement {
    private static final String MENU_NAME = "Close Game";

    private CloseGameElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myMenuItem.setOnAction(event -> {
            myController.getEnvironment().deleteCurrentLevel();
            myController.getCanvasViewController().refresh();
        });
    }
}
