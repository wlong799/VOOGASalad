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
	private VBox physicsSettingsBox;
	private CheckBox herosToCollideCheckBox, enemiesToCollideCheckBox, blockToCollideCheckBox, toApplyPhysicsCheckBox;

	public PhysicsConfiguringView(AuthoringController controller) {
		super(controller);
	}
	
	public void setSprite(ISprite sprite) {
		mySprite = sprite;
	}
	
	@Override
	public Parent getUI() {
		return physicsSettingsBox;
	}

	@Override
	protected void initUI() {
		myComponentPhysicsSettings = new ComponentPhysicsSettings(mySprite);

		herosToCollideCheckBox = new CheckBox("Collide with Heros");
		herosToCollideCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithHeros(new_val);
		});
		herosToCollideCheckBox.setSelected(mySprite.getAffectedByPhysics());

		enemiesToCollideCheckBox = new CheckBox("Collide with Enemies");
		enemiesToCollideCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithEnemies(new_val);
		});	
		enemiesToCollideCheckBox.setSelected(mySprite.getAffectedByPhysics());

		blockToCollideCheckBox = new CheckBox("Collide with Blocks");
		blockToCollideCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithBlock(new_val);
		});
		blockToCollideCheckBox.setSelected(mySprite.getAffectedByPhysics());

		toApplyPhysicsCheckBox = new CheckBox("Apply Physics");
		toApplyPhysicsCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.makePhysicsApplicable(new_val);
		});
		toApplyPhysicsCheckBox.setSelected(mySprite.getAffectedByPhysics());
		
		physicsSettingsBox.getChildren().addAll(herosToCollideCheckBox, enemiesToCollideCheckBox, blockToCollideCheckBox,toApplyPhysicsCheckBox);
	}

	@Override
	protected void updateLayoutSelf() {
		// TODO Auto-generated method stub
		
	}

}
