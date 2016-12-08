package authoring.view.inspector.settings.sprite;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.InspectorComponentView.ITextChangeHandler;
import game_object.core.ISprite;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PositionConfiguringView extends AbstractView {
	private SpriteView mySpriteView;
	private ISprite mySprite;
	private VBox myConfigs, myXBox, myYBox, myZBox, myWidthBox, myHeightBox;

	public PositionConfiguringView(AuthoringController controller) {
		super(controller);
	}
	
	public void setSpriteView(SpriteView spriteView) {
		mySpriteView = spriteView;
		initUI();
	}

	@Override
	protected void initUI() {
		mySprite = mySpriteView.getSprite();
		myXBox = makeDoubleInputBox("Position X", mySprite.getPosition().getX(), 
				(newVal) -> {
					mySpriteView.setAbsolutePositionX(Double.parseDouble(newVal));
				});
		myYBox = makeDoubleInputBox("Position Y", mySprite.getPosition().getY(), 
				(newVal) -> {
					mySpriteView.setAbsolutePositionY(Double.parseDouble(newVal));
				});
		myZBox = makeDoubleInputBox("Position Z", mySprite.getPosition().getZ(),
				(newVal) -> {
					mySpriteView.setAbsolutePositionZ(Double.parseDouble(newVal));
				});
		myWidthBox = makeDoubleInputBox("Width", mySprite.getDimension().getWidth(),
				(newVal) -> {
					mySpriteView.setDimensionWidth(Double.parseDouble(newVal));
				});
		myHeightBox = makeDoubleInputBox("Height", mySprite.getDimension().getHeight(),
				(newVal) -> {
					mySpriteView.setDimensionHeight(Double.parseDouble(newVal));
				});
		myConfigs = new VBox();
		myConfigs.getChildren().addAll(myXBox, myYBox, myZBox, myWidthBox, myHeightBox);
	}
	
	public Parent getUI() {
        return myConfigs;
    }

	@Override
	protected void updateLayoutSelf() {
	}
	
	private VBox makeDoubleInputBox(String title, double defaultValue, 
			ITextChangeHandler handler) {
		VBox box = new VBox();
		Label label = new Label(title);
		label.setFont(Font.font("Segoe UI Semibold"));
		TextField textField = new TextField(defaultValue + "");
		box.getChildren().addAll(label, textField);
		box.setPadding(new Insets(5,5,5,5));
		textField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				handler.handle(textField.getText());
			}
		});
		textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				handler.handle(textField.getText());
			}
		});
		return box;
	}

}
