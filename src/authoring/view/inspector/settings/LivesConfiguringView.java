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

    Spinner<Integer> myLifeSpinner;
    private Hero myHero;

    private ResourceBundle myInspectorProperties;
    private ResourceBundle myLanguageResourceBundle;

    public LivesConfiguringView(AuthoringController controller, Hero hero) {
        super(controller);
        myHero = hero;
    }

    @Override
    public void initializeSettings() {
    	myLanguageResourceBundle = super.getController().getEnvironment().getLanguageResourceBundle();
        myLifeSpinner.valueProperty().addListener((obs, oldValue, newValue) ->
                updateSpriteLives(newValue));
    }

    @Override
    protected void initUI() {
        super.initUI();
        myInspectorProperties = ResourceBundles.inspectorProperties;
        myLabel.setText(myLanguageResourceBundle.getString("configLives"));
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
