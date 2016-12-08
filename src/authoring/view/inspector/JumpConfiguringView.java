package authoring.view.inspector;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.ui.SliderBox;
import authoring.view.AbstractView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import resources.constants.ResourceBundles;

public class JumpConfiguringView extends AbstractView {
	
	private Hero myHero;
	private SliderBox numJumpBox;
	private SliderBox jumpUnitBox;
	private VBox myBox;
	private ResourceBundle componentProperties;

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
		myBox = new VBox();
		myBox.setPadding(new Insets(
				Double.parseDouble(componentProperties.getString("COMPONENT_PADDING")),
				Double.parseDouble(componentProperties.getString("COMPONENT_PADDING")),
				Double.parseDouble(componentProperties.getString("COMPONENT_PADDING")),
				Double.parseDouble(componentProperties.getString("COMPONENT_PADDING"))));
		this.updateLayout();
	}

	@Override
	protected void updateLayoutSelf() {
	}
	
	private void initSliders() {
		numJumpBox = new SliderBox(
				"Number of Jumps", 
				Double.parseDouble(componentProperties.getString("MIN_NUMBER_JUMPS")), 
				Double.parseDouble(componentProperties.getString("MAX_NUMBER_JUMPS")), 
				myHero.getMaxNumberOfJumps(), 
				Double.parseDouble(componentProperties.getString("JUMP_INCREMENT")), 
				(obv, oldVal, newVal) -> {
			myHero.setMaxNumberOfJumps(newVal.intValue());
		});
		jumpUnitBox = new SliderBox(
				"Jump Unit", 
				Double.parseDouble(componentProperties.getString("MIN_JUMP_UNIT")), 
				Double.parseDouble(componentProperties.getString("MAX_JUMP_UNIT")), 
				myHero.getJumpingUnit(), 
				Double.parseDouble(componentProperties.getString("JUMP_UNIT_INCREMENT")), 
				(obv, oldVal, newVal) -> {
			myHero.setJumpingUnit(newVal.doubleValue());
		});
		myBox.getChildren().addAll(numJumpBox.getBox(), jumpUnitBox.getBox());
	}

}
