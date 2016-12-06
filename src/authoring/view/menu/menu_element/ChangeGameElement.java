package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.core.Game;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

import java.util.*;

/**
 * Switch the currently active editable game.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class ChangeGameElement extends AbstractGameMenuElement implements Observer {
    private static final String MENU_NAME = "Change Game";
    private static final String GAME_PREFIX = "Game ";

    private ToggleGroup myToggleGroup;
    private Map<Game, RadioMenuItem> myCurrentItems;

    private ChangeGameElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myController.getEnvironment().addObserver(this);
        myToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (myToggleGroup.getSelectedToggle() != null) {
                Game game = (Game) myToggleGroup.getSelectedToggle().getUserData();
                if (game != myController.getEnvironment().getCurrentGame()) {
                    myController.getEnvironment().setCurrentGame(game);
                    // TODO: 12/5/16 replace CanvasController to use Observer
                    myController.getCanvasViewController().refresh();
                }
            }
        });
    }

    private void refreshAvailableGames() {
        ((Menu) myMenuItem).getItems().clear();
        myToggleGroup.getToggles().removeAll();
        myCurrentItems.clear();
        List<Game> availableGames = myController.getEnvironment().getAvailableGames();
        for (int i = 0; i < availableGames.size(); i++) {
            Game game = availableGames.get(i);
            // TODO: 12/5/16 Add game metadata and use name of game instead of index
            RadioMenuItem radioMenuItem = new RadioMenuItem(GAME_PREFIX + (i + 1));
            radioMenuItem.setUserData(game);
            radioMenuItem.setToggleGroup(myToggleGroup);
            myCurrentItems.put(game, radioMenuItem);
            ((Menu) myMenuItem).getItems().add(radioMenuItem);
        }
    }

    @Override
    protected void initMenuItem(String menuItemName) {
        myMenuItem = new Menu(menuItemName);
        myToggleGroup = new ToggleGroup();
        myCurrentItems = new HashMap<>();
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshAvailableGames();
        RadioMenuItem radioMenuItem = myCurrentItems.get(myController.getEnvironment().getCurrentGame());
        if (radioMenuItem != null) {
            radioMenuItem.setSelected(true);
        }
    }
}
