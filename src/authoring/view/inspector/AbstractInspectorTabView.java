package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.view.AbstractView;
import authoring.view.inspector.settings.AbstractSettingsView;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.TextInputBoxView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import sun.security.krb5.internal.PAData;

import java.util.Arrays;
import java.util.Observer;

/**
 * Abstract super class for all tabs within inspector view.
 *
 * @author Will Long
 */
public abstract class AbstractInspectorTabView extends AbstractView implements Observer {
    private ScrollPane myScrollPane;
    private VBox myContent;

    public AbstractInspectorTabView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        getController().addObserver(this);
        getController().getEnvironment().addObserver(this);
        myContent = new VBox();
        myContent.setPadding(new Insets(UIConstants.TEST_GAME_PADDING));
        myContent.setAlignment(Pos.CENTER);
        myScrollPane = new ScrollPane();
        myScrollPane.setContent(myContent);
        myScrollPane.setFitToWidth(true);
        addUI(myScrollPane);
    }

    @Override
    protected void updateLayoutSelf() {
        myScrollPane.setPrefWidth(getWidth());
        myScrollPane.setPrefHeight(getHeight());
        myContent.setPrefWidth(getWidth());
        getSubViews().forEach(subView -> subView.setWidth(getWidth() - 2 * UIConstants.TEST_GAME_PADDING));
    }

    protected void addSettingsView(AbstractSettingsView settingsView) {
        settingsView.initializeSettings();
        addSubView(settingsView);
        myContent.getChildren().add(settingsView.getUI());
    }

    protected void addSettingsViews(AbstractSettingsView... settingsViews) {
        Arrays.stream(settingsViews).forEach(this::addSettingsView);
    }
}