package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.inspector.settings.CheckBoxView;
import authoring.view.inspector.settings.SliderBoxView;
import authoring.view.run.TestGameView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.physics.PhysicsParameters;
import game_object.level.Level;
import resources.ResourceBundles;

import java.util.ResourceBundle;

public class InspectorLevelView extends AbstractInspectorTabView {
    private Level myLevel;
    private SliderBoxView myGravityBox, myAirFrictionBox, myGroundFrictionBox, myThresholdMaxBox, myThresholdMinBox;
    private CheckBoxView myGridSnapCheckBox;
    private ResourceBundle myLevelProperties;

    public InspectorLevelView(AuthoringController controller) {
        super(controller);
    }

    public void setLevel(Level level) {
        myLevel = level;
        updateUI();
    }

    /**
     * Updates physics value when there is a current level.
     */
    private void updateUI() {
        if (myLevel == null) {
            return;
        }
        PhysicsParameters param = myLevel.getPhysicsParameters();
        myGravityBox.setValue(param.getGravity());
        myAirFrictionBox.setValue(param.getAirFriction());
        myGroundFrictionBox.setValue(param.getGroundFriction());
        myThresholdMinBox.setValue(param.getMinThreshold());
        myThresholdMaxBox.setValue(param.getMaxThreshold());
    }

    @Override
    protected void initUI() {
        super.initUI();
        myLevelProperties = ResourceBundles.testGameProperties;
        fillInBox();
    }

    private void fillInBox() {
        myGravityBox = new SliderBoxView(
                getController(),
                "Gravity",
                Double.parseDouble(myLevelProperties.getString("GRAVITY_SLIDER_MIN")),
                Double.parseDouble(myLevelProperties.getString("GRAVITY_SLIDER_MAX")),
                Double.parseDouble(myLevelProperties.getString("GRAVITY_DEFAULT_VALUE")),
                Double.parseDouble(myLevelProperties.getString("GRAVITY_INTERVALS")),
                (obv, oldVal, newVal) -> setParameter(PhysicsParameterSetOptions.GRAVITY, newVal.doubleValue()));
        myAirFrictionBox = new SliderBoxView(
                getController(),
                "Air Friction",
                Double.parseDouble(myLevelProperties.getString("AIR_FRICTION_SLIDER_MIN")),
                Double.parseDouble(myLevelProperties.getString("AIR_FRICTION_SLIDER_MAX")),
                Double.parseDouble(myLevelProperties.getString("AIR_FRICTION_DEFAULT_VALUE")),
                Double.parseDouble(myLevelProperties.getString("AIR_FRICTION_INTERVALS")),
                (obv, oldVal, newVal) -> setParameter(PhysicsParameterSetOptions.AIRFRICTION, newVal.doubleValue()));
        myGroundFrictionBox = new SliderBoxView(
                getController(),
                "Ground Friction",
                Double.parseDouble(myLevelProperties.getString("GROUND_FRICTION_SLIDER_MIN")),
                Double.parseDouble(myLevelProperties.getString("GROUND_FRICTION_SLIDER_MAX")),
                Double.parseDouble(myLevelProperties.getString("GROUND_FRICTION_DEFAULT_VALUE")),
                Double.parseDouble(myLevelProperties.getString("GROUND_FRICTION_INTERVALS")),
                (obv, oldVal, newVal) -> setParameter(PhysicsParameterSetOptions.GROUNDFRICTION, newVal.doubleValue()));
        myThresholdMaxBox = new SliderBoxView(
                getController(),
                "Max Threshold",
                Double.parseDouble(myLevelProperties.getString("MAX_THRESHOLD_SLIDER_MIN")),
                Double.parseDouble(myLevelProperties.getString("MAX_THRESHOLD_SLIDER_MAX")),
                Double.parseDouble(myLevelProperties.getString("MAX_THRESHOLD_DEFAULT_VALUE")),
                Double.parseDouble(myLevelProperties.getString("MAX_THRESHOLD_INTERVALS")),
                (obv, oldVal, newVal) -> this.setParameter(PhysicsParameterSetOptions.MAXTHRESHOLD, newVal.doubleValue()));
        myThresholdMinBox = new SliderBoxView(
                getController(),
                "Min Threshold",
                Double.parseDouble(myLevelProperties.getString("MIN_THRESHOLD_SLIDER_MIN")),
                Double.parseDouble(myLevelProperties.getString("MIN_THRESHOLD_SLIDER_MAX")),
                Double.parseDouble(myLevelProperties.getString("MIN_THRESHOLD_DEFAULT_VALUE")),
                Double.parseDouble(myLevelProperties.getString("MIN_THRESHOLD_INTERVALS")),
                (obv, oldVal, newVal) -> this.setParameter(PhysicsParameterSetOptions.MINTHRESHOLD, newVal.doubleValue()));
        myGridSnapCheckBox = new CheckBoxView(getController(), "Snap to Grid", getController().getCanvasController().getSnapToGrid(),
                ((observable, oldValue, newValue) -> getController().getCanvasController().setSnapToGrid(newValue)));
        addSettingsViews(myGravityBox, myAirFrictionBox, myGroundFrictionBox, myThresholdMaxBox, myThresholdMinBox, myGridSnapCheckBox);
    }

    private void setParameter(PhysicsParameterSetOptions option, double value) {
        if (getParentView() instanceof InspectorView) {
            getController().setParameter(myLevel, option, value);
        } else if (getParentView() instanceof TestGameView) {
            getController().getTestGameController().setParameter(option, value);
        }
    }
}
