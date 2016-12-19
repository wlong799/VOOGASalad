package authoring.view.menu;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

import authoring.AuthoringController;
import authoring.ui.DialogFactory;
import authoring.view.menu.menu_element.NullMenu;
import authoring.view.menu.menu_element.NullMenuElement;

/**
 * Responsible for creating the overall menu bar at the top of the application. Uses reflection to access the necessary
 * private constructors. Functionality of individual elements is implemented through polymorphism of the
 * setFunctionality() method within AbstractGameMenuElement. Designed to make extending GameMenuView as simple as
 * possible. Any exceptions encountered during creation are output at the end using a dialog box.
 *
 * @author Will Long
 * @version 12/19/16
 */
@SuppressWarnings("unchecked")
public class GameMenuFactory {
    private static ResourceBundle myLanguageResourceBundle;
    private static List<MenuCreationException> myExceptions;

    public static GameMenuView createGameMenuView(AuthoringController controller) {
        myExceptions = new ArrayList<>();
        myLanguageResourceBundle = controller.getEnvironment().getLanguageResourceBundle();

        GameMenuView gameMenuView;
        try {
            Constructor<GameMenuView> gameMenuViewConstructor =
                    GameMenuView.class.getDeclaredConstructor(AuthoringController.class);
            gameMenuViewConstructor.setAccessible(true);
            gameMenuView = gameMenuViewConstructor.newInstance(controller);
        } catch (Exception e) {
            addCreationException(GameMenuView.class.getName());
            displayErrorDialog();
            return null;
        }

        Arrays.stream(GameMenuPreferences.values())
                .map(gameMenuPreferences -> createGameMenu(gameMenuPreferences, controller))
                .forEach(gameMenuView::addGameMenu);

        displayErrorDialog();
        return gameMenuView;
    }

    private static AbstractGameMenu createGameMenu(GameMenuPreferences gameMenuPreferences,
                                                   AuthoringController controller) {
        String menuName = gameMenuPreferences.getGameMenuName();
        try {
            menuName = myLanguageResourceBundle.getString(menuName);
        } catch (MissingResourceException e) {
            addResourceException(menuName);
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
            addCreationException(menuClass);
            gameMenu = new NullMenu();
        }

        Arrays.stream(menuElementClasses).map(menuElementClass -> createGameMenuElement(menuElementClass, controller))
                .forEach(gameMenu::addGameMenuElement);
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
            addCreationException(menuElementClass);
            gameMenuElement = new NullMenuElement();
        }
        return gameMenuElement;
    }

    private static void addResourceException(String missingResourceName) {
        myExceptions.add(
                new MenuCreationException(
                        myLanguageResourceBundle.getString("MENU_MISSING_NAME_EXCEPTION") + " " + missingResourceName));
    }

    private static void addCreationException(String errorClassName) {
        myExceptions.add(
                new MenuCreationException(
                        myLanguageResourceBundle.getString("MENU_CREATION_EXCEPTION") + " " + errorClassName));
    }

    private static void displayErrorDialog() {
        if (myExceptions.size() == 0) {
            return;
        }
        String title = myLanguageResourceBundle.getString("MENU_ERROR_TITLE");
        String header = myLanguageResourceBundle.getString("MENU_ERROR_HEADER");
        String content = myExceptions.stream().map(Throwable::getMessage).collect(Collectors.joining("\n"));
        DialogFactory.showErrorDialog(title, header, content);
    }
}
