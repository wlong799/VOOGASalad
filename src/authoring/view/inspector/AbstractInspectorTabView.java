package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.inspector.settings.AbstractSettingsView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import resources.ResourceBundles;

import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Abstract super class for all tabs within inspector view.
 *
 * @author Will Long
 */
public abstract class AbstractInspectorTabView extends AbstractView {
    private ScrollPane myScrollPane;
    private VBox myContent;
    private ResourceBundle myTestGameProperties;

    public AbstractInspectorTabView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        myTestGameProperties = ResourceBundles.testGameProperties;

        myContent = new VBox();
        myContent.setPadding(new Insets(Double.parseDouble(myTestGameProperties.getString("TEST_GAME_PADDING"))));
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
        getSubViews().forEach(subView -> subView.setWidth(getWidth() - 10 * Double.parseDouble(myTestGameProperties.getString("TEST_GAME_PADDING"))));
    }

    protected void addSettingsView(AbstractSettingsView settingsView) {
        settingsView.initializeSettings();
        addSubView(settingsView);
        myContent.getChildren().add(settingsView.getUI());
    }

    protected void addSettingsViews(AbstractSettingsView... settingsViews) {
        Arrays.stream(settingsViews).forEach(this::addSettingsView);
    }

    protected void clearSettingsView() {
        myContent.getChildren().clear();
        getSubViews().forEach(subView -> subView.setParentView(null));
        getSubViews().clear();
    }
    
    protected void removeSettingsView(AbstractSettingsView settingsView){
        myContent.getChildren().remove(settingsView);
    }
}