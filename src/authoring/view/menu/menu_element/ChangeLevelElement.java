package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.level.Level;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

import java.util.*;

/**
 * Switch the currently active editable level.
 *
 * @author Will Long
 * @version 11/28/16
 */
public class ChangeLevelElement extends AbstractGameMenuElement implements Observer {
    private static final String MENU_NAME = "Change Level";

    private ToggleGroup myToggleGroup;
    private Map<Level, RadioMenuItem> myCurrentItems;

    private ChangeLevelElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myController.getEnvironment().addObserver(this);
        myToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (myToggleGroup.getSelectedToggle() != null) {
                Level level = (Level) myToggleGroup.getSelectedToggle().getUserData();
                if (level != myController.getEnvironment().getCurrentLevel()) {
                    myController.getEnvironment().setCurrentLevel(level);
                    // TODO: 12/5/16 replace CanvasController to use Observer
                    myController.getCanvasController().refresh();
                }
            }
        });
    }

    private void refreshAvailableLevels() {
        ((Menu) myMenuItem).getItems().clear();
        myToggleGroup.getToggles().removeAll();
        myCurrentItems.clear();
        List<Level> availableLevels = myController.getEnvironment().getCurrentGame().getAllLevelsReadOnly();
        for (Level level : availableLevels) {
            RadioMenuItem radioMenuItem = new RadioMenuItem(level.getId());
            radioMenuItem.setUserData(level);
            radioMenuItem.setToggleGroup(myToggleGroup);
            myCurrentItems.put(level, radioMenuItem);
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
        refreshAvailableLevels();
        RadioMenuItem radioMenuItem = myCurrentItems.get(myController.getEnvironment().getCurrentLevel());
        if (radioMenuItem != null) {
            radioMenuItem.setSelected(true);
        }
    }
}
