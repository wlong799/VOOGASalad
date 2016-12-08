package authoring.view.inspector.settings.sprite;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.scene.control.Spinner;
import resources.ResourceBundles;

public class LivesConfiguringView extends AbstractView {
	Spinner<Integer> myLifeSpinner;
	private ResourceBundle myInspectorProperties;
	private ISprite myHero;
	
	public LivesConfiguringView(AuthoringController controller) {
		super(controller);
	}

	public void setSprite(ISprite sprite) {
        myHero = (Hero) sprite;
    }
	
	@Override
	protected void initUI() {
		myInspectorProperties = ResourceBundles.inspectorProperties;
		myLifeSpinner = new Spinner<Integer>(
				Integer.parseInt(myInspectorProperties.getString("LIFE_MIN")),
				Integer.parseInt(myInspectorProperties.getString("LIFE_MAX")),
				Integer.parseInt(myInspectorProperties.getString("LIFE_DEFAULT")),
				Integer.parseInt(myInspectorProperties.getString("LIFE_INCREMENT"))
		);
		myLifeSpinner.valueProperty().addListener((obs, oldValue, newValue) -> 
			updateSpriteLives(newValue));
	}

	@Override
	protected void updateLayoutSelf() {
	}
	
	private void updateSpriteLives(Integer newLifeNumber) {
		//FOR BACKEND TO DO
	}

}
