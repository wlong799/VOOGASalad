package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.core.Game;

/**
 * Close currently active game.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class CloseGameElement extends AbstractGameMenuElement {

    private CloseGameElement(AuthoringController controller) {
        super(controller.getEnvironment().getLanguageResourceBundle().getString("closeGame"), controller);
    }

    @Override
    protected void setFunctionality() {
        myMenuItem.setOnAction(event -> {
            Game game = myController.getEnvironment().getCurrentGame();
            myController.getEnvironment().removeGame(game);
        });
    }
}
