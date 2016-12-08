package authoring.settings;

import authoring.AuthoringController;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 * Provides an easy way to create a text input myBox with a title, default value, and action to perform when the text is
 * changed.
 *
 * @author Will Long, Bill Yu
 */
public class TextInputBox extends AbstractSettingsView {
    private String myTitle;
    private String myDefaultText;
    private ITextChangeHandler myHandler;

    private TextField myTextField;

    public TextInputBox(AuthoringController controller, String title, String defaultText, ITextChangeHandler handler) {
        super(controller);
        myTitle = title;
        myDefaultText = defaultText;
        myHandler = handler;
    }

    @Override
    protected void initUI() {
        super.initUI();
        myTextField = new TextField();
        myContent.getChildren().add(myTextField);
    }

    @Override
    protected void updateLayoutSelf() {
        super.updateLayoutSelf();
        myTextField.setPrefWidth(getWidth());
    }

    @Override
    public void initializeSettings() {
        myLabel.setText(myTitle);
        myTextField.setText(myDefaultText);
        myTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                myHandler.handle(myTextField.getText());
            }
        });
        myTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                myHandler.handle(myTextField.getText());
            }
        });
    }
}
