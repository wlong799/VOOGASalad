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

	private ScrollPane scrollPane;
	private SpriteView inspectedSpriteView;
	private ISprite sprite;
	private VBox configs;
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
		this.inspectedSpriteView = spView;
		updateUI();
	}

	@Override
	protected void initUI() {
		configs = new VBox();
		scrollPane = new ScrollPane();
		scrollPane.setContent(configs);
		scrollPane.setFitToWidth(true);
		this.addUI(scrollPane);
		updateUI();
	}

	@Override
	protected void updateLayoutSelf() {
		scrollPane.setPrefWidth(this.getWidth());
		scrollPane.setPrefHeight(this.getHeight());
	}

	private void updateUI() {
		configs.getChildren().clear();
		
		if (inspectedSpriteView == null) {
			noComponentSelected();
			return;
		}
		
		addPositionConfigurationView();
		addActionView();
		addPhysicsConfiguringView();
		
		if (sprite instanceof Hero) {
			configs.getChildren().add(myActionView.getUI());
		}
	}
	
	private void noComponentSelected() {
		Label noInspected = new Label("No Component Selected");
		configs.getChildren().add(noInspected);
	}
	
	private void addPositionConfigurationView() {
		myPositionConfigurationView = new PositionConfiguringView(this.getController());
		myPositionConfigurationView.setSpriteView(inspectedSpriteView);
		this.addSubView(myPositionConfigurationView);
	}
	
	private void addActionView() {
		myActionView = new ActionConfiguringView(this.getController());
		myActionView.setSprite(sprite);
		this.addSubView(myActionView);
	}
	
	private void addPhysicsConfiguringView() {
		myPhysicsConfiguringView = new PhysicsConfiguringView(this.getController());
		myPhysicsConfiguringView.setSprite(sprite);
		this.addSubView(myPhysicsConfiguringView);
	}

}