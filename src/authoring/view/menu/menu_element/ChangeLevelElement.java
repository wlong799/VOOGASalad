package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import game_object.LevelGenerator;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

/**
 * Switch the currently active editable level.
 *
 * @author Will Long
 * @version 11/17/16
 */
public class ChangeLevelElement extends AbstractGameMenuElement {
    private static final String MENU_NAME = "Change Level";
    private static final String LEVEL_PREFIX = "Level ";
    private ToggleGroup myToggleGroup;

    private ChangeLevelElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myToggleGroup = new ToggleGroup();
        myToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            int index = (int) newValue.getUserData();
            myController.getEnvironment().setCurrentLevel(index);
        });
        myMenuItem.
        myMenuItem.setOnAction(event -> {
            refreshLevelOptions();
        });
    }

    private void refreshLevelOptions() {
        ((Menu) myMenuItem).getItems().clear();
        myToggleGroup.getToggles().removeAll();
        for (int i = 1; i <= myController.getEnvironment().getCurrentGame().getAllLevels().size(); i++) {
            RadioMenuItem radioMenuItem = new RadioMenuItem(LEVEL_PREFIX + i);
            radioMenuItem.setUserData(i);
            radioMenuItem.setToggleGroup(myToggleGroup);
            ((Menu) myMenuItem).getItems().add(radioMenuItem);
        }
    }

    @Override
    protected void setMyMenuItem(String menuItemName) {
        myMenuItem = new Menu(menuItemName);
    }
}
