package authoring.view.menu;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.menu.menu_element.SimpleGameMenu;

/**
 * Responsible for creating the overall menu bar at the top of the application. Uses reflection to access the necessary
 * private constructors. Functionality of individual elements is implemented through polymorphism of the
 * setFunctionality() method within AbstractGameMenuElement. Designed to make extending GameMenuView as simple as
 * possible.
 *
 * @author Will Long
 * @version 12/19/16
 */
@SuppressWarnings("unchecked")
public class GameMenuFactory {
    private static ResourceBundle myLanguageResourceBundle;

    public static GameMenuView createGameMenuView(AuthoringController controller) {
        GameMenuView gameMenuView;
        myLanguageResourceBundle = controller.getEnvironment().getLanguageResourceBundle();
        try {
            Constructor<GameMenuView> gameMenuViewConstructor = GameMenuView.class.getDeclaredConstructor(
                    AuthoringController.class);
            gameMenuViewConstructor.setAccessible(true);
            gameMenuView = gameMenuViewConstructor.newInstance(controller);
        } catch (Exception e) {
            // TODO: 12/19/16 ERROR CHECK
            return null;
        }
        Arrays.stream(GameMenuPreferences.values())
                .map(gameMenuPreferences -> createGameMenu(gameMenuPreferences, controller))
                .filter(gameMenu -> gameMenu != null).forEach(gameMenuView::addGameMenu);
        return gameMenuView;
    }

    private static AbstractGameMenu createGameMenu(GameMenuPreferences gameMenuPreferences,
                                                   AuthoringController controller) {
        String menuName = gameMenuPreferences.getGameMenuName();
        try {
            menuName = myLanguageResourceBundle.getString(menuName);
        } catch (MissingResourceException e) {
            // TODO: 12/19/16 EXCEPTION HANDLING
        }
        String menuClass = gameMenuPreferences.getMenuClass();
        String[] menuElementClasses = gameMenuPreferences.getGameMenuElementClasses();
        AbstractGameMenu gameMenu;
        try {
            Class<? extends AbstractGameMenu> gameMenuClass =
                    (Class<? extends AbstractGameMenu>) Class.forName(menuClass);
            Constructor<? extends AbstractGameMenu> gameMenuConstructor =
                    gameMenuClass.getDeclaredConstructor(String.class, AuthoringController.class);
            gameMenuConstructor.setAccessible(true);
            gameMenu = gameMenuConstructor.newInstance(menuName, controller);
        } catch (Exception e) {
            // TODO: 12/19/16 ERROR CHECK
            return null;
        }
        Arrays.stream(menuElementClasses).map(menuElementClass -> createGameMenuElement(menuElementClass, controller)).
                filter(gameMenuElement -> gameMenuElement != null).forEach(gameMenu::addGameMenuElement);
        return gameMenu;
    }

    private static AbstractGameMenuElement createGameMenuElement(String menuElementClass,
                                                                 AuthoringController controller) {
        AbstractGameMenuElement gameMenuElement;
        try {
            Class<? extends AbstractGameMenuElement> gameMenuElementClass =
                    (Class<? extends AbstractGameMenuElement>) Class.forName(menuElementClass);
            Constructor<? extends AbstractGameMenuElement> gameMenuElementConstructor =
                    gameMenuElementClass.getDeclaredConstructor(AuthoringController.class);
            gameMenuElementConstructor.setAccessible(true);
            gameMenuElement = gameMenuElementConstructor.newInstance(controller);
        } catch (Exception e) {
            // TODO: 12/19/16 ERROR CHECK
            return null;
        }
        return gameMenuElement;
    }
}
