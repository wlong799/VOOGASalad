package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.settings.sprite.PositionConfiguringView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InspectorComponentView extends AbstractView {

	private ScrollPane scrollPane;
	private SpriteView inspectedSpriteView;
	private ISprite sprite;
	private VBox configs;
	private VBox xBox;
	private VBox yBox;
	private VBox zBox;
	private VBox widthBox;
	private VBox heightBox;
	private CheckBox herosCollisionCB, enemiesCollisionCB, blockCollisionCB, applyPhysics;
	private ComponentPhysicsSettings componentPhysicsSettings;
	private ActionConfiguringView myActionView;
	private PositionConfiguringView myPositionConfigurationView;

	public interface ITextChangeHandler {
		void handle(String newVal);
	}

	public InspectorComponentView(AuthoringController controller) {
		super(controller);
	}
	
	public void setInspectedSpriteView(SpriteView spView) {
		this.inspectedSpriteView = spView;
		updateUI();
	}

	@Override
	protected void initUI() {
		configs = new VBox();
		scrollPane = new ScrollPane();
		scrollPane.setContent(configs);
		scrollPane.setFitToWidth(true);
		this.addUI(scrollPane);
		updateUI();
	}

	@Override
	protected void updateLayoutSelf() {
		scrollPane.setPrefWidth(this.getWidth());
		scrollPane.setPrefHeight(this.getHeight());
	}

	private void updateUI() {
		configs.getChildren().clear();
		if (inspectedSpriteView == null) {
			Label noInspected = new Label("No Component Selected");
			configs.getChildren().add(noInspected);
			return;
		}
		
		myPositionConfigurationView = new PositionConfiguringView(this.getController());
		myPositionConfigurationView.setSpriteView(inspectedSpriteView);
		this.addSubView(myPositionConfigurationView);
		
		myActionView = new ActionConfiguringView(this.getController());
		myActionView.setSprite(sprite);
		this.addSubView(myActionView);

		initCheckBoxes();

		configs.getChildren().addAll(herosCollisionCB,
				enemiesCollisionCB, blockCollisionCB, applyPhysics);
		if (sprite instanceof Hero) {
			configs.getChildren().add(myActionView.getUI());
		}
	}

	private void initCheckBoxes() {
		componentPhysicsSettings = new ComponentPhysicsSettings(sprite);

		herosCollisionCB = new CheckBox("Collide with Heros");
		herosCollisionCB.selectedProperty().addListener((obv, old_val, new_val) -> {
			componentPhysicsSettings.setCollisionSettingWithHeros(new_val);
		});
		herosCollisionCB.setSelected(sprite.getAffectedByPhysics());

		enemiesCollisionCB = new CheckBox("Collide with Enemies");
		enemiesCollisionCB.selectedProperty().addListener((obv, old_val, new_val) -> {
			componentPhysicsSettings.setCollisionSettingWithEnemies(new_val);
		});	
		enemiesCollisionCB.setSelected(sprite.getAffectedByPhysics());

		blockCollisionCB = new CheckBox("Collide with Blocks");
		blockCollisionCB.selectedProperty().addListener((obv, old_val, new_val) -> {
			componentPhysicsSettings.setCollisionSettingWithBlock(new_val);
		});
		blockCollisionCB.setSelected(sprite.getAffectedByPhysics());

		applyPhysics = new CheckBox("Apply Physics");
		applyPhysics.selectedProperty().addListener((obv, old_val, new_val) -> {
			componentPhysicsSettings.makePhysicsApplicable(new_val);
		});
		applyPhysics.setSelected(sprite.getAffectedByPhysics());
	}

}