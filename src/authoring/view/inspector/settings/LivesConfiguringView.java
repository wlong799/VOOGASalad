package authoring.view.inspector.settings;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.inspector.settings.AbstractSettingsView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.scene.control.Spinner;
import resources.ResourceBundles;

public class LivesConfiguringView extends AbstractSettingsView {
    private static final String LABEL_TEXT = "CONFIGURE LIVES";

    Spinner<Integer> myLifeSpinner;
    private Hero myHero;

    private ResourceBundle myInspectorProperties;

    public LivesConfiguringView(AuthoringController controller, Hero hero) {
        super(controller);
        myHero = hero;
    }

    @Override
    public void initializeSettings() {
        myLifeSpinner.valueProperty().addListener((obs, oldValue, newValue) ->
                updateSpriteLives(newValue));
    }

    @Override
    protected void initUI() {
        super.initUI();
        myInspectorProperties = ResourceBundles.inspectorProperties;
        myLabel.setText(LABEL_TEXT);
        myLifeSpinner = new Spinner<>(
                Integer.parseInt(myInspectorProperties.getString("LIFE_MIN")),
                Integer.parseInt(myInspectorProperties.getString("LIFE_MAX")),
                Integer.parseInt(myInspectorProperties.getString("LIFE_DEFAULT")),
                Integer.parseInt(myInspectorProperties.getString("LIFE_INCREMENT"))
        );
        myContent.getChildren().add(myLifeSpinner);
    }

    @Override
    protected void updateLayoutSelf() {
        myLifeSpinner.setPrefWidth(getWidth());
    }

    private void updateSpriteLives(Integer newLifeNumber) {
        //FOR BACKEND TO DO
    }

}
