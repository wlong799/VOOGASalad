package authoring.view.run;

import authoring.AuthoringController;
import authoring.ui.SliderBox;
import authoring.view.AbstractView;
import game_engine.physics.PhysicsParameterSetOptions;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class TestGameConfiguringView extends AbstractView {
	
	private VBox myBox;

	public TestGameConfiguringView(AuthoringController controller) {
		super(controller);
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
		//TODO: change set engine's parameter to change level's
		SliderBox gravityBox = new SliderBox(
				"Gravity", 
				0, 
				100, 
				50,//default 
				1, 
				(obv, oldVal, newVal) -> {
			this.getController().getTestGameController().getEngine()
				.setParameter(PhysicsParameterSetOptions.GRAVITY, newVal.doubleValue());
		});
		SliderBox afBox = new SliderBox(
				"Air Friction", 
				0, 
				1, 
				0,//default 
				0.1, 
				(obv, oldVal, newVal) -> {
			this.getController().getTestGameController().getEngine()
				.setParameter(PhysicsParameterSetOptions.AIRFRICTION, newVal.doubleValue());
		});
		SliderBox gfBox = new SliderBox(
				"Ground Friction", 
				0, 
				1, 
				0.1,//default 
				0.1, 
				(obv, oldVal, newVal) -> {
			this.getController().getTestGameController().getEngine()
				.setParameter(PhysicsParameterSetOptions.GROUNDFRICTION, newVal.doubleValue());
		});
		gfBox.getBox().setFocusTraversable(false);
		SliderBox tmaxBox = new SliderBox(
				"Max Threshold", 
				0, 
				1000, 
				1000,//default 
				1000, 
				(obv, oldVal, newVal) -> {
			this.getController().getTestGameController().getEngine()
				.setParameter(PhysicsParameterSetOptions.MAXTHRESHOLD, newVal.doubleValue());
		});
		tmaxBox.getBox().setFocusTraversable(false);
		SliderBox tminBox = new SliderBox(
				"Min Threshold", 
				0, 
				100, 
				1,//default 
				1, 
				(obv, oldVal, newVal) -> {
			this.getController().getTestGameController().getEngine()
				.setParameter(PhysicsParameterSetOptions.MINTHRESHOLD, newVal.doubleValue());
		});
		tminBox.getBox().setFocusTraversable(false);
		
		myBox.getChildren().addAll(
				gravityBox.getBox(), afBox.getBox(), gfBox.getBox(), tminBox.getBox(), tmaxBox.getBox());
		myBox.getChildren().forEach(box->box.setFocusTraversable(true));
		//myBox.requestFocus();
		//myBox.getChildren().get(0).setDisable(true);
	}

}
