package authoring.view.inspector.settings;

import authoring.AuthoringController;
import game_object.core.ISprite;
import javafx.scene.control.Label;

public class LabelView extends AbstractSettingsView {
	private ISprite mySprite;
	private Label mySpriteTypeLabel;

	public LabelView(AuthoringController controller, ISprite sprite) {
		super(controller);
		mySprite = sprite;
	}

	@Override
	public void initializeSettings() {
		mySpriteTypeLabel = new Label(mySprite.getClass().getSimpleName());
		mySpriteTypeLabel.getStyleClass().add("sprite-label");
		myContent.getChildren().add(mySpriteTypeLabel);
	}

}
