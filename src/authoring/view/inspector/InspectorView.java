package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.updating.IPublisher;
import authoring.updating.ISubscriber;
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

public class InspectorView extends AbstractView implements ISubscriber {

	private SpriteView inspectedSpriteView;
	private ISprite sprite;
	private VBox configs;
	private VBox xBox;
	private VBox yBox;
	private VBox zBox;
	private VBox widthBox;
	private VBox heightBox;
	private ScrollPane inspectorPane;
	private CheckBox herosCollisionCB, enemiesCollisionCB, blockCollisionCB, applyPhysics;
	private ComponentPhysicsSettings componentPhysicsSettings;
	private ActionConfiguringView myActionView;

	public interface ITextChangeHandler {
		void handle(String newVal);
	}

	public InspectorView(AuthoringController controller) {
		super(controller);
	}

	@Override
	public void didUpdate(IPublisher target) {
		if (target instanceof AuthoringController) {
			inspectedSpriteView = ((AuthoringController) target).getSelectedSpriteView();
			updateUI();
		}
	}

	@Override
	protected void initUI() {
		this.getController().addSubscriber(this);
		inspectorPane = new ScrollPane();
		inspectorPane.setFitToWidth(true);
		configs = new VBox();
		inspectorPane.setContent(configs);

		this.addUI(inspectorPane);
	}

	@Override
	protected void updateLayoutSelf() {
		inspectorPane.setPrefWidth(this.getWidth());
		inspectorPane.setPrefHeight(this.getHeight());
	}

	private void updateUI() {
		configs.getChildren().clear();
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