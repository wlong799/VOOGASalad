package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;
import java.util.Map;

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
    private Map<Integer, RadioMenuItem> myCurrentItems;

    private ChangeLevelElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

    @Override
    protected void setFunctionality() {
        myController.getEnvironment().getNumLevels().addListener(observable -> {
            refreshAvailableLevels();
        });
        myController.getEnvironment().getCurrentLevelIndex().addListener((observable, oldValue, newValue) -> {
            RadioMenuItem radioMenuItem = myCurrentItems.get(newValue.intValue());
            if (radioMenuItem != null) {
                myCurrentItems.get(newValue.intValue()).setSelected(true);
            }
        });
        myToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (myToggleGroup.getSelectedToggle() != null) {
                int index = (int) newValue.getUserData();
                if (index != myController.getEnvironment().getCurrentLevelIndex().get()) {
                    myController.getEnvironment().setCurrentLevel(index);
                    myController.getCanvasViewController().refresh();
                }
            }
        });
        refreshAvailableLevels();
    }

    private void refreshAvailableLevels() {
        ((Menu) myMenuItem).getItems().clear();
        myToggleGroup.getToggles().removeAll();
        myCurrentItems.clear();
        for (int i = 0; i < myController.getEnvironment().getNumLevels().get(); i++) {
            RadioMenuItem radioMenuItem = new RadioMenuItem(LEVEL_PREFIX + (i + 1));
            radioMenuItem.setUserData(i);
            if (myController.getEnvironment().getCurrentLevelIndex().get() == i) {
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
    }
}
