package authoring.view.inspector.settings;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.inspector.settings.SliderBoxView;
import authoring.view.AbstractView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import resources.ResourceBundles;

public class JumpConfiguringView extends AbstractView {
    private Hero myHero;
    private SliderBoxView numJumpBox;
    private SliderBoxView jumpUnitBox;
    private VBox myBox;
    private ResourceBundle componentProperties;
    private ResourceBundle myLanguageResourceBundle;
    private CheckBox infiniteJumps;

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
        componentProperties = ResourceBundles.componentProperties;
        myLanguageResourceBundle = super.getController().getEnvironment().getLanguageResourceBundle();
        myBox = new VBox();
        myBox.setPadding(new Insets(Double.parseDouble(componentProperties.getString("COMPONENT_PADDING"))));
        updateLayout();
    }

    @Override
    protected void updateLayoutSelf() {
    }

    private void initSliders() {
    	infiniteJumps = new CheckBox(myLanguageResourceBundle.getString("infiniteJumps"));
    	infiniteJumps.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				myHero.setMaxNumberOfJumps(Integer.MAX_VALUE);
				numJumpBox.disable();
			}
        });
    	
    	numJumpBox = new SliderBoxView(
                getController(),
                myLanguageResourceBundle.getString("noJumps"),
                Double.parseDouble(componentProperties.getString("MIN_NUMBER_JUMPS")),
                Double.parseDouble(componentProperties.getString("MAX_NUMBER_JUMPS")),
                myHero.getMaxNumberOfJumps(),
                Double.parseDouble(componentProperties.getString("JUMP_INCREMENT")),
                (obv, oldVal, newVal) -> {
                    myHero.setMaxNumberOfJumps(newVal.intValue());
                });
        jumpUnitBox = new SliderBoxView(
                getController(),
                myLanguageResourceBundle.getString("jumpUnit"),
                Double.parseDouble(componentProperties.getString("MIN_JUMP_UNIT")),
                Double.parseDouble(componentProperties.getString("MAX_JUMP_UNIT")),
                myHero.getJumpingUnit(),
                Double.parseDouble(componentProperties.getString("JUMP_UNIT_INCREMENT")),
                (obv, oldVal, newVal) -> {
                    myHero.setJumpingUnit(newVal.doubleValue());
                });
        
        
        myBox.getChildren().addAll(numJumpBox.getUI(), jumpUnitBox.getUI());
    }
}
