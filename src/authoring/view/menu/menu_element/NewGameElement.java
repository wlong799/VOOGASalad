package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.LevelGenerator;
import game_object.core.Game;

/**
 * Adds a new game to the workspace.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class NewGameElement extends AbstractGameMenuElement {
    private static final String MENU_NAME = "New Game";

    private NewGameElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myMenuItem.setOnAction(event -> {
            Game game = new Game();
            game.addLevel(LevelGenerator.getTestLevelB());
            myController.getEnvironment().addGame(game);
            myController.getCanvasViewController().refresh();
        });
    }
}
