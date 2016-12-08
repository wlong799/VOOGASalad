package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.settings.sprite.PhysicsConfiguringView;
import authoring.view.inspector.settings.sprite.PositionConfiguringView;
import game_object.character.Hero;
import game_object.core.ISprite;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class InspectorComponentView extends AbstractView {

	private ScrollPane myScrollPane;
	private SpriteView myInspectedSpriteView;
	private ISprite mySprite;
	private VBox myConfigsBox;
	private ActionConfiguringView myActionView;
	private PositionConfiguringView myPositionConfigurationView;
	private PhysicsConfiguringView myPhysicsConfiguringView;

	public interface ITextChangeHandler {
		void handle(String newVal);
	}

	public InspectorComponentView(AuthoringController controller) {
		super(controller);
	}
	
	public void setInspectedSpriteView(SpriteView spView) {
		myInspectedSpriteView = spView;
		updateUI();
	}

	@Override
	protected void initUI() {
		myConfigsBox = new VBox();
		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myConfigsBox);
		myScrollPane.setFitToWidth(true);
		this.addUI(myScrollPane);
		updateUI();
	}

	@Override
	protected void updateLayoutSelf() {
		myScrollPane.setPrefWidth(this.getWidth());
		myScrollPane.setPrefHeight(this.getHeight());
	}

	private void updateUI() {
		myConfigsBox.getChildren().clear();
		
		if (myInspectedSpriteView == null) {
			noComponentSelected();
			return;
		}
		
		addPositionConfigurationView();
		addActionView();
		addPhysicsConfiguringView();
		
		if (mySprite instanceof Hero) {
			myConfigsBox.getChildren().add(myActionView.getUI());
		}
	}
	
	private void noComponentSelected() {
		Label noInspected = new Label("No Component Selected");
		myConfigsBox.getChildren().add(noInspected);
	}
	
	private void addPositionConfigurationView() {
		myPositionConfigurationView = new PositionConfiguringView(this.getController());
		myPositionConfigurationView.setSpriteView(myInspectedSpriteView);
		this.addSubView(myPositionConfigurationView);
	}
	
	private void addActionView() {
		myActionView = new ActionConfiguringView(this.getController());
		myActionView.setSprite(mySprite);
		this.addSubView(myActionView);
	}
	
	private void addPhysicsConfiguringView() {
		myPhysicsConfiguringView = new PhysicsConfiguringView(this.getController());
		myPhysicsConfiguringView.setSprite(mySprite);
		this.addSubView(myPhysicsConfiguringView);
	}

}