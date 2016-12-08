package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.inspector.settings.AbstractSettingsView;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.TextInputBoxView;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

/**
 * Abstract super class for all tabs within inspector view.
 *
 * @author Will Long
 */
public class AbstractInspectorTabView extends AbstractView {
    private ScrollPane myScrollPane;
    private VBox myContent;

    public AbstractInspectorTabView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        myContent = new VBox();
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
        getSubViews().forEach(subView -> subView.setWidth(getWidth()));
    }

    protected void addSettingsView(AbstractSettingsView settingsView) {
        settingsView.initializeSettings();
        addSubView(settingsView);
        myContent.getChildren().add(settingsView.getUI());
    }

    protected void addSettingsViews(AbstractSettingsView ... settingsViews) {
        Arrays.stream(settingsViews).forEach(this::addSettingsView);
    }
}