package authoring.settings;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * AbstractSettingsView provides the abstract superclass for generic settings elements to be used in various places within
 * authoring environment (e.g. inspector, menu). Each settings view is contained within a labeled VBox.
 *
 * @author Will Long
 */
public abstract class AbstractSettingsView extends AbstractView {
    private static final double PADDING = 5;
    private static final String FONT = "Segoe UI Semibold";

    protected VBox myContent;
    protected Label myLabel;

    public AbstractSettingsView(AuthoringController controller) {
        super(controller);
    }

    /**
     * After UI has been initialized, this should be called to fill in the settings view with the appropriate default
     * settings and labels.
     */
    public abstract void initializeSettings();

    @Override
    protected void initUI() {
        myContent = new VBox();
        myContent.setPadding(new Insets(PADDING));
        myLabel = new Label();
        myLabel.setFont(Font.font(FONT));
        myContent.getChildren().add(myLabel);
        addUI(myContent);
    }

    @Override
    protected void updateLayoutSelf() {
        myContent.setPrefWidth(getWidth());
        myLabel.setPrefWidth(getWidth());
    }
}
