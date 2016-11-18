package ui.menu;

import ui.AuthoringController;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * Responsible for creating the overall menu bar at the top of the application. Uses reflection to access the necessary
 * private constructors. Functionality of individual elements is implemented through polymorphism of the
 * setFunctionality() method within AbstractGameMenuElement.
 *
 * @author Will Long
 * @version 11/17/16
 */
public class GameMenuFactory {
    private static final String MENU_ELEMENT_PACKAGE = "ui.menu.menu_element.";

    /**
     * GameMenuInfo enum holds the information that should be loaded into the GameMenuView. Each element in the enum
     * contains the name of the menu to add, and an array of Strings naming the menu elements within the menu.
     */
    private enum GameMenuInfo {
        FILE("File", new String[]{}),
        LEVEL("Level", new String[]{"NewLevelElement"}),
        VIEW("View", new String[]{}),
        KEYBOARD("Keyboard", new String[]{});

        private String myGameMenuName;
        private String[] myGameMenuElementClasses;

        GameMenuInfo(String menuName, String[] menuElementClasses) {
            myGameMenuName = menuName;
            myGameMenuElementClasses = menuElementClasses;
        }

        String getGameMenuName() {
            return myGameMenuName;
        }

        String[] getGameMenuElementClasses() {
            return myGameMenuElementClasses;
        }
    }

    public static GameMenuView createGameMenuView(AuthoringController controller) {
        GameMenuView gameMenuView;
        try {
            Constructor<GameMenuView> gameMenuViewConstructor = GameMenuView.class.getDeclaredConstructor(
                    AuthoringController.class);
            gameMenuViewConstructor.setAccessible(true);
            gameMenuView = gameMenuViewConstructor.newInstance(controller);
        } catch (Exception e) {
            System.err.println("Reflection error.");
            e.printStackTrace();
            return null;
        }
        Arrays.stream(GameMenuInfo.values()).map(gameMenuInfo -> createGameMenu(gameMenuInfo, controller)).filter(
                gameMenu -> gameMenu != null).forEach(gameMenuView::addGameMenu);
        return gameMenuView;
    }

    private static GameMenu createGameMenu(GameMenuInfo gameMenuInfo, AuthoringController controller) {
        String menuName = gameMenuInfo.getGameMenuName();
        String[] menuElementClasses = gameMenuInfo.getGameMenuElementClasses();
        GameMenu gameMenu;
        try {
            Constructor<GameMenu> gameMenuConstructor = GameMenu.class.getDeclaredConstructor(String.class);
            gameMenuConstructor.setAccessible(true);
            gameMenu = gameMenuConstructor.newInstance(menuName);
        } catch (Exception e) {
            System.err.println("Reflection error.");
            e.printStackTrace();
            return null;
        }
        Arrays.stream(menuElementClasses).map(menuElementClass -> createGameMenuElement(menuElementClass, controller)).
                filter(gameMenuElement -> gameMenuElement != null).forEach(gameMenu::addGameMenuElement);
        return gameMenu;
    }

    private static AbstractGameMenuElement createGameMenuElement(String menuElementClass,
                                                                 AuthoringController controller) {
        menuElementClass = MENU_ELEMENT_PACKAGE + menuElementClass;
        AbstractGameMenuElement gameMenuElement;
        try {
            @SuppressWarnings("unchecked")
            Class<? extends AbstractGameMenuElement> gameMenuElementClass =
                    (Class<? extends AbstractGameMenuElement>) Class.forName(menuElementClass);
            Constructor<? extends AbstractGameMenuElement> gameMenuElementConstructor = gameMenuElementClass.
                    getDeclaredConstructor(AuthoringController.class);
            gameMenuElementConstructor.setAccessible(true);
            gameMenuElement = gameMenuElementConstructor.newInstance(controller);
        } catch (Exception e) {
            System.err.println("Reflection error.");
            e.printStackTrace();
            return null;
        }
        return gameMenuElement;
    }
}
