package authoring.view.inspector.settings;

/**
 * Provides a simple way to handle events where a String has been changed and needs to be accessed.
 *
 * @author Will Long, Bill Yu
 */
public interface ITextChangeHandler {
    void handle(String newValue);
}
