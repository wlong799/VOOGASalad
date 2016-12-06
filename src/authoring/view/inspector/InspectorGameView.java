package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
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

public class InspectorGameView extends AbstractView {

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

	public interface ITextChangeHandler {
		void handle(String newVal);
	}

	public InspectorGameView(AuthoringController controller) {
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
		sprite = inspectedSpriteView.getSprite();
		xBox = makeDoubleInputBox("Position X", sprite.getPosition().getX(), 
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionX(Double.parseDouble(newVal));
				});
		yBox = makeDoubleInputBox("Position Y", sprite.getPosition().getY(), 
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionY(Double.parseDouble(newVal));
				});
		zBox = makeDoubleInputBox("Position Z", sprite.getPosition().getZ(),
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionZ(Double.parseDouble(newVal));
				});
		widthBox = makeDoubleInputBox("Width", sprite.getDimension().getWidth(),
				(newVal) -> {
					inspectedSpriteView.setDimensionWidth(Double.parseDouble(newVal));
				});
		heightBox = makeDoubleInputBox("Height", sprite.getDimension().getHeight(),
				(newVal) -> {
					inspectedSpriteView.setDimensionHeight(Double.parseDouble(newVal));
				});
		myActionView = new ActionConfiguringView(this.getController());
		myActionView.setSprite(sprite);
		this.addSubView(myActionView);

		initCheckBoxes();

		configs.getChildren().addAll(xBox, yBox, zBox, widthBox, heightBox, herosCollisionCB,
				enemiesCollisionCB, blockCollisionCB, applyPhysics);
		if (sprite instanceof Hero) {
			configs.getChildren().add(myActionView.getUI());
		}
	}

	private VBox makeDoubleInputBox(String title, double defaultValue, 
			ITextChangeHandler handler) {
		VBox box = new VBox();
		Label label = new Label(title);
		label.setFont(Font.font("Segoe UI Semibold"));
		TextField tf = new TextField(defaultValue + "");
		box.getChildren().addAll(label, tf);
		box.setPadding(new Insets(5,5,5,5));
		tf.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				handler.handle(tf.getText());
			}
		});
		tf.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				handler.handle(tf.getText());
			}
		});
		return box;
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