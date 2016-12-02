package authoring.view.run;

import authoring.AuthoringController;
import authoring.ui.SliderBox;
import authoring.view.AbstractView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.physics.PhysicsParameters;
import game_object.level.Level;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class TestGameConfiguringView extends AbstractView {
	
	private VBox myBox;
	private SliderBox gravityBox;
	private SliderBox afBox;
	private SliderBox gfBox;
	private SliderBox tmaxBox;
	private SliderBox tminBox;

	public TestGameConfiguringView(AuthoringController controller) {
		super(controller);
	}
	
	/**
	 * updates physics value when there is a current level
	 */
	public void updateUI() {
		Level current = this.getController().getEnvironment().getCurrentGame().getCurrentLevel();
		if (current == null) return;
		PhysicsParameters param = current.getPhysicsParameters();
		gravityBox.setValue(param.getGravity());
		afBox.setValue(param.getAirFriction());
		gfBox.setValue(param.getGroundFriction());
		tminBox.setValue(param.getMinThreshold());
		tmaxBox.setValue(param.getMaxThreshold());
	}
	
	@Override
	protected void initUI() {
		myBox = new VBox();
		myBox.setSpacing(10);
		myBox.setPadding(new Insets(5, 5, 5, 5));
		myBox.setStyle("-fx-background-color: linen;");
		fillInBox();
		this.addUI(myBox);
	}

	@Override
	protected void updateLayoutSelf() {
		myBox.setPrefWidth(this.getWidth());
		myBox.setPrefHeight(this.getHeight());
	}
	
	private void fillInBox() {
		gravityBox = new SliderBox(
				"Gravity", 
				0, 
				100, 
				50,//default 
				1, 
				(obv, oldVal, newVal) -> {
			this.getController().setParameter(PhysicsParameterSetOptions.GRAVITY, newVal.doubleValue());
		});
		afBox = new SliderBox(
				"Air Friction", 
				0, 
				1, 
				0,//default 
				0.1, 
				(obv, oldVal, newVal) -> {
			this.getController().setParameter(PhysicsParameterSetOptions.AIRFRICTION, newVal.doubleValue());
		});
		gfBox = new SliderBox(
				"Ground Friction", 
				0, 
				1, 
				0.1,//default 
				0.1, 
				(obv, oldVal, newVal) -> {
			this.getController().setParameter(PhysicsParameterSetOptions.GROUNDFRICTION, newVal.doubleValue());
		});
		gfBox.getBox().setFocusTraversable(false);
		tmaxBox = new SliderBox(
				"Max Threshold", 
				0, 
				1000, 
				1000,//default 
				1000, 
				(obv, oldVal, newVal) -> {
			this.getController().setParameter(PhysicsParameterSetOptions.MAXTHRESHOLD, newVal.doubleValue());
		});
		tmaxBox.getBox().setFocusTraversable(false);
		tminBox = new SliderBox(
				"Min Threshold", 
				0, 
				100, 
				1,//default 
				1, 
				(obv, oldVal, newVal) -> {
			this.getController().setParameter(PhysicsParameterSetOptions.MINTHRESHOLD, newVal.doubleValue());
		});
		tminBox.getBox().setFocusTraversable(false);
		
		myBox.getChildren().addAll(
				gravityBox.getBox(), afBox.getBox(), gfBox.getBox(), tminBox.getBox(), tmaxBox.getBox());
		myBox.getChildren().forEach(box->box.setFocusTraversable(true));
	}

}
