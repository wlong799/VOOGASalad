package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.inspector.settings.SliderBoxView;
import authoring.view.AbstractView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class JumpConfiguringView extends AbstractView {

    private Hero myHero;
    private SliderBoxView numJumpBox;
    private SliderBoxView jumpUnitBox;
    private VBox myBox;

    public JumpConfiguringView(AuthoringController controller) {
        super(controller);
    }

    public void setSprite(ISprite sprite) {
        myHero = (Hero) sprite;
        initSliders();
    }

    @Override
    public Parent getUI() {
        return myBox;
    }

    @Override
    protected void initUI() {
        myBox = new VBox();
        myBox.setPadding(new Insets(5, 5, 5, 5));
        this.updateLayout();
    }

    @Override
    protected void updateLayoutSelf() {
    }

    private void initSliders() {
        numJumpBox = new SliderBoxView(
                getController(),
                "Number of Jumps",
                0,
                10,
                myHero.getMaxNumberOfJumps(),
                1,
                (obv, oldVal, newVal) -> {
                    myHero.setMaxNumberOfJumps(newVal.intValue());
                });
        jumpUnitBox = new SliderBoxView(
                getController(),
                "Jump Unit",
                0,
                500,
                myHero.getJumpingUnit(),
                100,
                (obv, oldVal, newVal) -> {
                    myHero.setJumpingUnit(newVal.doubleValue());
                });
        myBox.getChildren().addAll(numJumpBox.getUI(), jumpUnitBox.getUI());
    }

}
