package authoring.view.inspector.settings;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import game_object.character.Hero;
import javafx.scene.control.Spinner;
import resources.ResourceBundles;

public class HealthPointConfiguringView extends AbstractSettingsView {
	private Spinner<Integer> myHPSpinner;
	private Hero myHero;
	private ResourceBundle myLanguageResourceBundle;
	private static ResourceBundle myInspectorProperties = ResourceBundles.inspectorProperties;

	public HealthPointConfiguringView(AuthoringController controller, Hero hero) {
		super(controller);
		myHero = hero;
	}

	@Override
	public void initializeSettings() {
		myHPSpinner.valueProperty().addListener((obs, oldValue, newValue) ->
		updateSpriteHealthPoints(newValue));
	}
	
	@Override
    protected void initUI() {
        super.initUI();
        myLanguageResourceBundle = getController().getEnvironment().getLanguageResourceBundle();
        myLabel.setText(myLanguageResourceBundle.getString("configHP"));
        myHPSpinner = new Spinner<>(
                Integer.parseInt(myInspectorProperties.getString("HP_MIN")),
                Integer.parseInt(myInspectorProperties.getString("HP_MAX")),
                Integer.parseInt(myInspectorProperties.getString("HP_DEFAULT")),
                Integer.parseInt(myInspectorProperties.getString("HP_INCREMENT"))
        );
        myContent.getChildren().add(myHPSpinner);
    }
	
	 @Override
	    protected void updateLayoutSelf() {
		 	myHPSpinner.setPrefWidth(getWidth());
	    }
	
	 private void updateSpriteHealthPoints(Integer newHealthPoint) {
	        myHero.setCurrentHP(newHealthPoint);
	    }
	
}
