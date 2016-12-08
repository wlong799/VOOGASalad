package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.settings.sprite.JumpConfiguringView;
import authoring.view.inspector.settings.sprite.ReachPointGoalConfiguringView;
import game_object.block.IBlock;
import game_object.character.Hero;
import game_object.constants.DefaultConstants;
import game_object.core.ISprite;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InspectorSpriteView extends AbstractView {

	private ScrollPane myScrollPane;
	private SpriteView myInspectedSpriteView;
	private ISprite mySprite;
	private VBox myConfigsBox;
	private VBox myXBox;
	private VBox myYBox;
	private VBox myZBox;
	private VBox myWidthBox;
	private VBox myHeightBox;
	private CheckBox myHerosCollisionCheckBox, myEnemiesCollisionCheckBox, myBlockCollisionCheckBox, myApplyPhysicsCheckBox;
	private ComponentPhysicsSettings myComponentPhysicsSettings;
	private ActionConfiguringView myActionView;
	private JumpConfiguringView myJumpView;
	private ReachPointGoalConfiguringView myReachPointGoal;

	public interface ITextChangeHandler {
		void handle(String newVal);
	}

	public InspectorSpriteView(AuthoringController controller) {
		super(controller);
	}
	
	public void setInspectedSpriteView(SpriteView spView) {
		this.myInspectedSpriteView = spView;
		updateUI();
	}

	@Override
	protected void initUI() {
		myConfigsBox = new VBox();
		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myConfigsBox);
		myScrollPane.setFitToWidth(true);
		
		this.addUI(myScrollPane);
		updateUI();
	}

	@Override
	protected void updateLayoutSelf() {
		myScrollPane.setPrefWidth(this.getWidth());
		myScrollPane.setPrefHeight(this.getHeight());
	}

	private void updateUI() {
		myConfigsBox.getChildren().clear();
		this.getSubViews().clear();
		if (myInspectedSpriteView == null) {
			Label noInspected = new Label("No Component Selected");
			myConfigsBox.getChildren().add(noInspected);
			return;
		}
		mySprite = myInspectedSpriteView.getSprite();
		myXBox = makeDoubleInputBox("Position X", mySprite.getPosition().getX(), 
				(newVal) -> {
					myInspectedSpriteView.setAbsolutePositionX(Double.parseDouble(newVal));
				});
		myYBox = makeDoubleInputBox("Position Y", mySprite.getPosition().getY(), 
				(newVal) -> {
					myInspectedSpriteView.setAbsolutePositionY(Double.parseDouble(newVal));
				});
		myZBox = makeDoubleInputBox("Position Z", mySprite.getPosition().getZ(),
				(newVal) -> {
					myInspectedSpriteView.setAbsolutePositionZ(Double.parseDouble(newVal));
				});
		myWidthBox = makeDoubleInputBox("Width", mySprite.getDimension().getWidth(),
				(newVal) -> {
					myInspectedSpriteView.setDimensionWidth(Double.parseDouble(newVal));
				});
		myHeightBox = makeDoubleInputBox("Height", mySprite.getDimension().getHeight(),
				(newVal) -> {
					myInspectedSpriteView.setDimensionHeight(Double.parseDouble(newVal));
				});
		initCheckBoxes();

		myConfigsBox.getChildren().addAll(myXBox, myYBox, myZBox, myWidthBox, myHeightBox, myHerosCollisionCheckBox,
				myEnemiesCollisionCheckBox, myBlockCollisionCheckBox, myApplyPhysicsCheckBox);
		
		if (mySprite instanceof IBlock) {
			myReachPointGoal = new ReachPointGoalConfiguringView(this.getController());
			myReachPointGoal.setUpReachPointGoalCheckBox(mySprite);
			this.addSubView(myReachPointGoal);
			myConfigsBox.getChildren().add(myReachPointGoal.getUI());
		}
		if (mySprite instanceof Hero) {
			myActionView = new ActionConfiguringView(this.getController());
			myActionView.setSprite(mySprite);
			this.addSubView(myActionView);
			myJumpView = new JumpConfiguringView(this.getController());
			myJumpView.setSprite(mySprite);
			this.addSubView(myJumpView);
			myConfigsBox.getChildren().addAll(myActionView.getUI(), myJumpView.getUI());
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
		myComponentPhysicsSettings = new ComponentPhysicsSettings(mySprite);

		myHerosCollisionCheckBox = new CheckBox("Collide with Heros");
		myHerosCollisionCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithHeros(new_val);
		});
		myHerosCollisionCheckBox.setSelected((mySprite.getCollisionBitMask() & DefaultConstants.HERO_CATEGORY_BIT_MASK)!=0);

		myEnemiesCollisionCheckBox = new CheckBox("Collide with Enemies");
		myEnemiesCollisionCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithEnemies(new_val);
		});	
		myEnemiesCollisionCheckBox.setSelected((mySprite.getCollisionBitMask() & DefaultConstants.ENEMY_CATEGORY_BIT_MASK)!=0);

		myBlockCollisionCheckBox = new CheckBox("Collide with Blocks");
		myBlockCollisionCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.setCollisionSettingWithBlock(new_val);
		});
		myBlockCollisionCheckBox.setSelected((mySprite.getCollisionBitMask() & DefaultConstants.BLOCK_CATEGORY_BIT_MASK)!=0);

		myApplyPhysicsCheckBox = new CheckBox("Apply Physics");
		myApplyPhysicsCheckBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			myComponentPhysicsSettings.makePhysicsApplicable(new_val);
		});
		myApplyPhysicsCheckBox.setSelected(mySprite.getAffectedByPhysics());
	}

}