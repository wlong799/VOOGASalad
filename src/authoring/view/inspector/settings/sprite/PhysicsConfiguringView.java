package authoring.view.inspector.settings.sprite;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.inspector.ComponentPhysicsSettings;
import game_object.core.ISprite;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class PhysicsConfiguringView extends AbstractView {
	private ISprite mySprite;
	private ComponentPhysicsSettings myComponentPhysicsSettings;
	private VBox myPhysicsSettingsBox;
	private CheckBox myHerosToCollideCheckBox, myEnemiesToCollideCheckBox, myBlockToCollideCheckBox, myToApplyPhysicsCheckBox;

	public PhysicsConfiguringView(AuthoringController controller) {
		super(controller);
	}
	
	public void setSprite(ISprite sprite) {
		mySprite = sprite;
	}
	
	@Override
	public Parent getUI() {
		return myPhysicsSettingsBox;
	}

	@Override
	protected void initUI() {
		myComponentPhysicsSettings = new ComponentPhysicsSettings(mySprite);

		myHerosToCollideCheckBox = new CheckBox("Collide with Heros");
		myHerosToCollideCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithHeros(new_val);
		});
		myHerosToCollideCheckBox.setSelected(mySprite.getAffectedByPhysics());

		myEnemiesToCollideCheckBox = new CheckBox("Collide with Enemies");
		myEnemiesToCollideCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithEnemies(new_val);
		});	
		myEnemiesToCollideCheckBox.setSelected(mySprite.getAffectedByPhysics());

		myBlockToCollideCheckBox = new CheckBox("Collide with Blocks");
		myBlockToCollideCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithBlock(new_val);
		});
		myBlockToCollideCheckBox.setSelected(mySprite.getAffectedByPhysics());

		myToApplyPhysicsCheckBox = new CheckBox("Apply Physics");
		myToApplyPhysicsCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.makePhysicsApplicable(new_val);
		});
		myToApplyPhysicsCheckBox.setSelected(mySprite.getAffectedByPhysics());
		
		myPhysicsSettingsBox.getChildren().addAll(myHerosToCollideCheckBox, myEnemiesToCollideCheckBox, myBlockToCollideCheckBox,myToApplyPhysicsCheckBox);
	}

	@Override
	protected void updateLayoutSelf() {
		// TODO Auto-generated method stub
		
	}

}
