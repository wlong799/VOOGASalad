package authoring.view.inspector.settings.sprite;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.inspector.settings.SliderBoxView;
import authoring.view.AbstractView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import resources.ResourceBundles;

public class JumpConfiguringView extends AbstractView {
    private Hero myHero;
    private SliderBoxView numJumpBox;
    private SliderBoxView jumpUnitBox;
    private VBox myBox;
    private ResourceBundle myComponentProperties;

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
        myComponentProperties = ResourceBundles.componentProperties;
        myBox = new VBox();
        myBox.setPadding(new Insets(Double.parseDouble(myComponentProperties.getString("COMPONENT_PADDING"))));
        updateLayout();
    }

    @Override
    protected void updateLayoutSelf() {
    }

    private void initSliders() {
        numJumpBox = new SliderBoxView(
                getController(),
                "Number of Jumps",
                Double.parseDouble(myComponentProperties.getString("MIN_NUMBER_JUMPS")),
                Double.parseDouble(myComponentProperties.getString("MAX_NUMBER_JUMPS")),
                myHero.getMaxNumberOfJumps(),
                Double.parseDouble(myComponentProperties.getString("JUMP_INCREMENT")),
                (obv, oldVal, newVal) -> {
                    myHero.setMaxNumberOfJumps(newVal.intValue());
                });
        jumpUnitBox = new SliderBoxView(
                getController(),
                "Jump Unit",
                Double.parseDouble(myComponentProperties.getString("MIN_JUMP_UNIT")),
                Double.parseDouble(myComponentProperties.getString("MAX_JUMP_UNIT")),
                myHero.getJumpingUnit(),
                Double.parseDouble(myComponentProperties.getString("JUMP_UNIT_INCREMENT")),
                (obv, oldVal, newVal) -> {
                    myHero.setJumpingUnit(newVal.doubleValue());
                });
        myBox.getChildren().addAll(numJumpBox.getUI(), jumpUnitBox.getUI());
    }
}
