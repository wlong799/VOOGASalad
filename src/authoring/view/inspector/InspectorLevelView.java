package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.ui.SliderBox;
import authoring.view.AbstractView;
import authoring.view.inspector.InspectorView;
import authoring.view.run.TestGameView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.physics.PhysicsParameters;
import game_object.level.Level;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.Observable;
import java.util.Observer;

public class InspectorLevelView extends AbstractView implements Observer {

    private VBox myBox;
    private SliderBox gravityBox;
    private SliderBox afBox;
    private SliderBox gfBox;
    private SliderBox tmaxBox;
    private SliderBox tminBox;
    private Level myLevel;

    public InspectorLevelView(AuthoringController controller) {
        super(controller);
    }

    public void setLevel(Level level) {
        myLevel = level;
        this.updateUI();
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
        getController().addObserver(this);
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
                    this.setParameter(PhysicsParameterSetOptions.GRAVITY, newVal.doubleValue());
                });
        afBox = new SliderBox(
                "Air Friction",
                0,
                1,
                0,//default
                0.1,
                (obv, oldVal, newVal) -> {
                    this.setParameter(PhysicsParameterSetOptions.AIRFRICTION, newVal.doubleValue());
                });
        gfBox = new SliderBox(
                "Ground Friction",
                0,
                1,
                0.1,//default
                0.1,
                (obv, oldVal, newVal) -> {
                    this.setParameter(PhysicsParameterSetOptions.GROUNDFRICTION, newVal.doubleValue());
                });
        gfBox.getBox().setFocusTraversable(false);
        tmaxBox = new SliderBox(
                "Max Threshold",
                0,
                1000,
                1000,//default
                1000,
                (obv, oldVal, newVal) -> {
                    this.setParameter(PhysicsParameterSetOptions.MAXTHRESHOLD, newVal.doubleValue());
                });
        tmaxBox.getBox().setFocusTraversable(false);
        tminBox = new SliderBox(
                "Min Threshold",
                0,
                100,
                1,//default
                1,
                (obv, oldVal, newVal) -> {
                    this.setParameter(PhysicsParameterSetOptions.MINTHRESHOLD, newVal.doubleValue());
                });
        tminBox.getBox().setFocusTraversable(false);

        myBox.getChildren().addAll(
                gravityBox.getBox(), afBox.getBox(), gfBox.getBox(), tminBox.getBox(), tmaxBox.getBox());
        myBox.getChildren().forEach(box -> box.setFocusTraversable(true));
    }

    private void setParameter(PhysicsParameterSetOptions option, double value) {
        if (this.getParentView() instanceof InspectorView) {
            this.getController().setParameter(myLevel, option, value);
        } else if (this.getParentView() instanceof TestGameView) {
            this.getController().getTestGameController().setParameter(option, value);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof AuthoringController) {
            updateUI();
        }
    }
}