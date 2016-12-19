package authoring.view.menu;

/**
 * Provides basic handling of exceptions when loading menu through reflection.
 *
 * @author Will Long
 * @version 12/19/16
 */
public class MenuCreationException extends Exception {
    public MenuCreationException(String message) {
        super(message);
    }
}
