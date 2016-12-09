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
    private SliderBoxView gravityBox, afBox, gfBox, tmaxBox, tminBox;
    private CheckBoxView editSettings;
    private ResourceBundle levelProperties;

    public InspectorLevelView(AuthoringController controller) {
        super(controller);
    }


    @Override
    protected void initUI() {
        super.initUI();
        levelProperties = ResourceBundles.testGameProperties;
        fillInBox();
    }

    private void fillInBox() {
        gravityBox = new SliderBoxView(
                getController(),
                "Gravity",
                Double.parseDouble(levelProperties.getString("GRAVITY_SLIDER_MIN")),
                Double.parseDouble(levelProperties.getString("GRAVITY_SLIDER_MAX")),
                Double.parseDouble(levelProperties.getString("GRAVITY_DEFAULT_VALUE")),
                Double.parseDouble(levelProperties.getString("GRAVITY_INTERVALS")),
                (obv, oldVal, newVal) -> setParameter(PhysicsParameterSetOptions.GRAVITY, newVal.doubleValue()));
        afBox = new SliderBoxView(
                getController(),
                "Air Friction",
                Double.parseDouble(levelProperties.getString("AIR_FRICTION_SLIDER_MIN")),
                Double.parseDouble(levelProperties.getString("AIR_FRICTION_SLIDER_MAX")),
                Double.parseDouble(levelProperties.getString("AIR_FRICTION_DEFAULT_VALUE")),
                Double.parseDouble(levelProperties.getString("AIR_FRICTION_INTERVALS")),
                (obv, oldVal, newVal) -> setParameter(PhysicsParameterSetOptions.AIRFRICTION, newVal.doubleValue()));
        gfBox = new SliderBoxView(
                getController(),
                "Ground Friction",
                Double.parseDouble(levelProperties.getString("GROUND_FRICTION_SLIDER_MIN")),
                Double.parseDouble(levelProperties.getString("GROUND_FRICTION_SLIDER_MAX")),
                Double.parseDouble(levelProperties.getString("GROUND_FRICTION_DEFAULT_VALUE")),
                Double.parseDouble(levelProperties.getString("GROUND_FRICTION_INTERVALS")),
                (obv, oldVal, newVal) -> setParameter(PhysicsParameterSetOptions.GROUNDFRICTION, newVal.doubleValue()));
        tmaxBox = new SliderBoxView(
                getController(),
                "Max Threshold",
                Double.parseDouble(levelProperties.getString("MAX_THRESHOLD_SLIDER_MIN")),
                Double.parseDouble(levelProperties.getString("MAX_THRESHOLD_SLIDER_MAX")),
                Double.parseDouble(levelProperties.getString("MAX_THRESHOLD_DEFAULT_VALUE")),
                Double.parseDouble(levelProperties.getString("MAX_THRESHOLD_INTERVALS")),
                (obv, oldVal, newVal) -> this.setParameter(PhysicsParameterSetOptions.MAXTHRESHOLD, newVal.doubleValue()));
        tminBox = new SliderBoxView(
                getController(),
                "Min Threshold",
                Double.parseDouble(levelProperties.getString("MIN_THRESHOLD_SLIDER_MIN")),
                Double.parseDouble(levelProperties.getString("MIN_THRESHOLD_SLIDER_MAX")),
                Double.parseDouble(levelProperties.getString("MIN_THRESHOLD_DEFAULT_VALUE")),
                Double.parseDouble(levelProperties.getString("MIN_THRESHOLD_INTERVALS")),
                (obv, oldVal, newVal) -> this.setParameter(PhysicsParameterSetOptions.MINTHRESHOLD, newVal.doubleValue()));
        editSettings = new CheckBoxView(getController(), "Snap to Grid", getController().getCanvasController().getSnapToGrid(),
                ((observable, oldValue, newValue) -> getController().getCanvasController().setSnapToGrid(newValue)));
        addSettingsViews(gravityBox, afBox, gfBox, tmaxBox, tminBox, editSettings);
    }

    private void setParameter(PhysicsParameterSetOptions option, double value) {
        if (getParentView() instanceof InspectorView) {
            getController().setParameter(myLevel, option, value);
        } else if (getParentView() instanceof TestGameView) {
            getController().getTestGameController().setParameter(option, value);
        }
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
        gravityBox.setValue(param.getGravity());
        afBox.setValue(param.getAirFriction());
        gfBox.setValue(param.getGroundFriction());
        tminBox.setValue(param.getMinThreshold());
        tmaxBox.setValue(param.getMaxThreshold());
    }
}
