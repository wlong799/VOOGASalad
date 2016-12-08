package authoring.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Provides an easy way to create a text input box with a title, default value, and action to perform when the text is
 * changed.
 *
 * @author Will Long, Bill Yu
 */
public class TextInputBox {
    private static final double PADDING = 5;
    private static final String FONT = "Segoe UI Semibold";

    private String myTitle;
    private String myDefaultText;
    private ITextChangeHandler myHandler;

    public TextInputBox(String title, String defaultText, ITextChangeHandler handler) {
        myTitle = title;
        myDefaultText = defaultText;
        myHandler = handler;
    }

    public VBox getTextInputBox() {
        VBox box = new VBox();
        Label label = new Label(myTitle);
        label.setFont(Font.font(FONT));
        TextField textField = new TextField(myDefaultText);
        box.getChildren().addAll(label, textField);
        box.setPadding(new Insets(PADDING));
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                myHandler.handle(textField.getText());
            }
        });
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                myHandler.handle(textField.getText());
            }
        });
        return box;
    }
}
