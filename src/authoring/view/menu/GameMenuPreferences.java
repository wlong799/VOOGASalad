package authoring.view.menu;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Stores the information to be loaded into GameMenuView by GameMenuFactory. Each enum constant describes a single
 * AbstractGameMenu to be loaded. Each enum has the name to be shown on the menu, the class of AbstractGameMenu to
 * create, and the names of all the classes of AbstractGameMenuElement to add. This is all created through reflection in
 * GameMenuFactory, providing an easy way to change the structure of the GameMenuView, or extend it to contain new
 * classes.
 *
 * @author Will Long
 * @version 12/19/16
 */
public enum GameMenuPreferences {
    FILE("file", "SimpleGameMenu",
            new String[]{
                    "NewGameElement",
                    "ChangeGameElement",
                    "SeparatorMenuElement",
                    "LoadElement",
                    "SaveElement",
                    "SeparatorMenuElement",
                    "CloseGameElement"
            }),

    LEVEL("asdf", "SimpleGameMenu",
            new String[]{
                    "NewLevelElement",
                    "ChangeLevelElement",
                    "DeleteLevelElement",
                    "SeparatorMenuElement",
                    "ChangeLevelBackgroundElement",
                    "RandomClusterElement"
            }),

    TEST("test", "SimpleGameMenu",
            new String[]{
                    "RunElement"
            });

    private static final String MENU_ELEMENT_PACKAGE = "authoring.view.menu.menu_element.";
    private String myGameMenuName;
    private String myMenuClass;
    private String[] myGameMenuElementClasses;

    GameMenuPreferences(String menuName, String menuClass, String[] menuElementClasses) {
        myGameMenuName = menuName;
        myMenuClass = MENU_ELEMENT_PACKAGE + menuClass;
        myGameMenuElementClasses = Arrays.stream(menuElementClasses)
                .map(simpleClassName -> MENU_ELEMENT_PACKAGE + simpleClassName)
                .collect(Collectors.toList()).toArray(new String[0]);
    }

    String getGameMenuName() {
        return myGameMenuName;
    }

    String getMenuClass() {
        return myMenuClass;
    }

    String[] getGameMenuElementClasses() {
        return myGameMenuElementClasses;
    }
}