package authoring.view.run;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.ui.SliderBox;
import authoring.updating.IPublisher;
import authoring.updating.ISubscriber;
import authoring.view.AbstractView;
import authoring.view.inspector.InspectorView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.physics.PhysicsParameters;
import game_object.level.Level;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import resources.constants.ResourceBundles;

public class TestGameConfiguringView extends AbstractView implements ISubscriber {
	
	private VBox myBox;
	private SliderBox gravityBox;
	private SliderBox afBox;
	private SliderBox gfBox;
	private SliderBox tmaxBox;
	private SliderBox tminBox;
	private Level myLevel;
	private ResourceBundle testGameProperties;

	public TestGameConfiguringView(AuthoringController controller) {
		super(controller);
	}
	
	public void setLevel(Level level) {
		myLevel = level;
		this.updateUI();
	}
	
	@Override
	public void didUpdate(IPublisher target) {
		if (target instanceof AuthoringController) {
			this.updateUI();
		}
	}
	
	@Override
	public Parent getUI() {
		return myBox;
	}
	
	/**
	 * updates physics value when there is a current level
	 */
	private void updateUI() {
		if (myLevel == null) return;
		PhysicsParameters param = myLevel.getPhysicsParameters();
		gravityBox.setValue(param.getGravity());
		afBox.setValue(param.getAirFriction());
		gfBox.setValue(param.getGroundFriction());
		tminBox.setValue(param.getMinThreshold());
		tmaxBox.setValue(param.getMaxThreshold());
	}
	
	@Override
	protected void initUI() {
		testGameProperties = ResourceBundles.testGameProperties;
		
		this.getController().addSubscriber(this);
		myBox = new VBox();
		myBox.setSpacing(10);
		myBox.setPadding(
				new Insets(
						Double.parseDouble(testGameProperties.getString("TEST_GAME_PADDING")), 
						Double.parseDouble(testGameProperties.getString("TEST_GAME_PADDING")), 
						Double.parseDouble(testGameProperties.getString("TEST_GAME_PADDING")), 
						Double.parseDouble(testGameProperties.getString("TEST_GAME_PADDING"))));
		fillInBox();
	}

	@Override
	protected void updateLayoutSelf() {
		myBox.setPrefWidth(this.getWidth());
		myBox.setPrefHeight(this.getHeight());
		myBox.setLayoutX(this.getPositionX());
	}
	
	private void fillInBox() {
		gravityBox = new SliderBox(
				"Gravity", 
				Double.parseDouble(testGameProperties.getString("GRAVITY_SLIDER_MIN")), 
				Double.parseDouble(testGameProperties.getString("GRAVITY_SLIDER_MAX")), 
				Double.parseDouble(testGameProperties.getString("GRAVITY_DEFAULT_VALUE")), 
				Double.parseDouble(testGameProperties.getString("GRAVITY_INTERVALS")), 
				(obv, oldVal, newVal) -> {
			this.setParameter(PhysicsParameterSetOptions.GRAVITY, newVal.doubleValue());
		});
		afBox = new SliderBox(
				"Air Friction", 
				Double.parseDouble(testGameProperties.getString("AIR_FRICTION_SLIDER_MIN")), 
				Double.parseDouble(testGameProperties.getString("AIR_FRICTION_SLIDER_MAX")), 
				Double.parseDouble(testGameProperties.getString("AIR_FRICTION_DEFAULT_VALUE")), 
				Double.parseDouble(testGameProperties.getString("AIR_FRICTION_INTERVALS")),
				(obv, oldVal, newVal) -> {
			this.setParameter(PhysicsParameterSetOptions.AIRFRICTION, newVal.doubleValue());
		});
		gfBox = new SliderBox(
				"Ground Friction", 
				Double.parseDouble(testGameProperties.getString("GROUND_FRICTION_SLIDER_MIN")), 
				Double.parseDouble(testGameProperties.getString("GROUND_FRICTION_SLIDER_MAX")), 
				Double.parseDouble(testGameProperties.getString("GROUND_FRICTION_DEFAULT_VALUE")), 
				Double.parseDouble(testGameProperties.getString("GROUND_FRICTION_INTERVALS")),
				(obv, oldVal, newVal) -> {
			this.setParameter(PhysicsParameterSetOptions.GROUNDFRICTION, newVal.doubleValue());
		});
		gfBox.getBox().setFocusTraversable(false);
		tmaxBox = new SliderBox(
				"Max Threshold", 
				Double.parseDouble(testGameProperties.getString("MAX_THRESHOLD_SLIDER_MIN")), 
				Double.parseDouble(testGameProperties.getString("MAX_THRESHOLD_SLIDER_MAX")), 
				Double.parseDouble(testGameProperties.getString("MAX_THRESHOLD_DEFAULT_VALUE")), 
				Double.parseDouble(testGameProperties.getString("MAX_THRESHOLD_INTERVALS")),
				(obv, oldVal, newVal) -> {
			this.setParameter(PhysicsParameterSetOptions.MAXTHRESHOLD, newVal.doubleValue());
		});
		tmaxBox.getBox().setFocusTraversable(false);
		tminBox = new SliderBox(
				"Min Threshold", 
				Double.parseDouble(testGameProperties.getString("MIN_THRESHOLD_SLIDER_MIN")), 
				Double.parseDouble(testGameProperties.getString("MIN_THRESHOLD_SLIDER_MAX")), 
				Double.parseDouble(testGameProperties.getString("MIN_THRESHOLD_DEFAULT_VALUE")), 
				Double.parseDouble(testGameProperties.getString("MIN_THRESHOLD_INTERVALS")),
				(obv, oldVal, newVal) -> {
			this.setParameter(PhysicsParameterSetOptions.MINTHRESHOLD, newVal.doubleValue());
		});
		tminBox.getBox().setFocusTraversable(false);
		
		myBox.getChildren().addAll(
				gravityBox.getBox(), afBox.getBox(), gfBox.getBox(), tminBox.getBox(), tmaxBox.getBox());
		myBox.getChildren().forEach(box->box.setFocusTraversable(true));
	}
	
	private void setParameter(PhysicsParameterSetOptions option, double value) {
		if (this.getParentView() instanceof InspectorView) {
			this.getController().setParameter(myLevel, option, value);
		}
		else if (this.getParentView() instanceof TestGameView) {
			this.getController().getTestGameController().setParameter(option, value);
		}
	}

}
