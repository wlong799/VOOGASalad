package authoring.view.inspector.settings;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import game_object.character.Hero;
import javafx.scene.control.Spinner;
import resources.ResourceBundles;

public class LivesConfiguringView extends AbstractSettingsView {

    Spinner<Integer> myLifeSpinner;
    private Hero myHero;

    private static ResourceBundle myInspectorProperties = ResourceBundles.inspectorProperties;
    private static ResourceBundle myLanguageResourceBundle = ResourceBundles.languageProperties;

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
