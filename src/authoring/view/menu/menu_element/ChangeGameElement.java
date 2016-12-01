package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * Switch the currently active editable game.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class ChangeGameElement extends AbstractGameMenuElement {
    private static final String MENU_NAME = "Change Game";
    private static final String GAME_PREFIX = "Game ";

    private ToggleGroup myToggleGroup;
    private Map<Integer, RadioMenuItem> myCurrentItems;

    private ChangeGameElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myController.getEnvironment().getNumGames().addListener(observable -> {
            refreshAvailableGames();
        });
        myController.getEnvironment().getCurrentGameIndex().addListener((observable, oldValue, newValue) -> {
            RadioMenuItem radioMenuItem = myCurrentItems.get(newValue.intValue());
            if (radioMenuItem != null) {
                myCurrentItems.get(newValue.intValue()).setSelected(true);
            }
        });
        myToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (myToggleGroup.getSelectedToggle() != null) {
                int index = (int) newValue.getUserData();
                if (index != myController.getEnvironment().getCurrentGameIndex().get()) {
                    myController.getEnvironment().setCurrentGame(index);
                    myController.getCanvasViewController().refresh();
                }
            }
        });
    }

    private void refreshAvailableGames() {
        ((Menu) myMenuItem).getItems().clear();
        myToggleGroup.getToggles().removeAll();
        myCurrentItems.clear();
        for (int i = 0; i < myController.getEnvironment().getNumGames().get(); i++) {
            RadioMenuItem radioMenuItem = new RadioMenuItem(GAME_PREFIX + (i + 1));
            radioMenuItem.setUserData(i);
            if (myController.getEnvironment().getCurrentGameIndex().get() == i) {
                radioMenuItem.setSelected(true);
            }
            radioMenuItem.setToggleGroup(myToggleGroup);
            myCurrentItems.put(i, radioMenuItem);
            ((Menu) myMenuItem).getItems().add(radioMenuItem);
        }
    }

    @Override
    protected void initMenuItem(String menuItemName) {
        myMenuItem = new Menu(menuItemName);
        myToggleGroup = new ToggleGroup();
        myCurrentItems = new HashMap<>();
        refreshAvailableGames();
    }
}
