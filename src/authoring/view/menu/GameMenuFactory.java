// This entire file is part of my masterpiece.
// Will Long

/*
 * MASTERPIECE EXPLANATION:
 *
 * GameMenuFactory contains the instantiation code necessary for creating a GameMenuView object. I decided to create the
 * class in this way, because as I added new elements to place in the menu, I realized it was getting increasingly
 * complicated to create the GameMenuView object. I also realized I was performing the same steps a lot (i.e. creating
 * a GameMenu container, instantiating a bunch of GameMenuElements to place in the GameMenu, adding the new GameMenu to
 * the GameMenuView, and then starting again with a new GameMenu). I decided to automate the process, using reflection
 * to load the proper menus and menu elements. This makes the entire creation process much simpler, and much more easy
 * to edit and extend. There are a few key features in this class that I believe make it good code.
 *
 * 1. Use of lambdas and reflection. This class relies heavily on both lambdas and reflection, both of which are
 * advanced Java features. The lambdas make it very easy to loop over all the necessary information to add in a concise
 * manner. Using reflection allows me to simply load the proper class names from the GameMenuPreferences file, and
 * instantiate the objects from there.
 *
 * 2. Error handling. I don't ignore errors, or just print them, or print their stack trace. Instead, I keep track of
 * any errors encountered throughout the creation process. At the end of the process, I use a dialog box to concisely
 * display all errors at once. It makes it very easy to see all the issues with the class, and trace them back
 * to their source.
 *
 * 3. Use of resource files. I avoid using any "magic variables" within the code, and instead load everything from
 * resource files. This makes it much more flexible to change to different locales in the future if necessary.
 *
 * 4. Null Object pattern. Instead of returning null when an error is encountered, and filtering out these results, I
 * use null objects that inherit from AbstractGameMenu/AbstractGameMenuElement. This is useful because I can still work
 * with them in the same way that I work with other instances of these super-classes. I don't have to worry about
 * checking for special cases.
 *
 * 5. Use of abstract class hierarchies. None of this would be possible without the use of abstract super-classes. Each
 * GameMenuView adds instances of AbstractGameMenu, which add instantiations of AbstractGameMenuElement. We don't know
 * which specific implementation will be used, but we can instead use the abstract class's available methods to
 * provide the functionality that we need. Each concrete class will then achieve its purpose through polymorphism.
 *
 * Altogether, this class embodies the open-closed principle. No source code has to be edited for the user to change
 * around the ordering of elements, or even add new elements. Instead, everything is very simply located within the
 * GameMenuPreferences. It is extremely easy to extend to new elements or structures. The user just needs to create
 * a concrete subclass of the proper abstract super-class, and add its name into GameMenuPreferences, and then
 * everything else will be handled automatically.
 */
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

    /**
     * Creates a new GameMenuView based on information provided in GameMenuPreferences.
     *
     * @param controller is top-level authoring controller.
     * @return a new GameMenuView object
     */
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
