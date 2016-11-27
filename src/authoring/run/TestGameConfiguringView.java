package authoring.run;

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
		SliderBox gravityBox = new SliderBox(
				"Gravity", 
				0, 
				100, 
				20, 
				1, 
				(obv, oldVal, newVal) -> {
			this.getController().getTestGameController().getEngine()
				.setParameter(PhysicsParameterSetOptions.GRAVITY, newVal.doubleValue());
		});
		myBox.getChildren().add(gravityBox.getBox());
	}

}
