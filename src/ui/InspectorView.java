package ui;

import game_object.core.ISprite;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InspectorView extends View implements Subscriber {
	
	private SpriteView inspectedSpriteView;
	private VBox configs;
	private VBox xBox;
	private VBox yBox;

	public InspectorView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	public void didUpdate(Publisher target) {
		if (target instanceof AuthoringController) {
			inspectedSpriteView = ((AuthoringController) target).getSelectedSpriteView();
			updateUI();
		}
	}
	
	@Override
	protected void initUI() {
		this.getController().addSubscriber(this);
		configs = new VBox();
		this.addUI(configs);
	}

	@Override
	protected void layoutSelf() {
		configs.setPrefWidth(this.getWidth());
		configs.setPrefHeight(this.getHeight());
	}
	
	private void updateUI() {
		configs.getChildren().clear();
		ISprite sprite = inspectedSpriteView.getSprite();
		xBox = makeDoubleInputBox("position X", sprite.getPosition().getX(), 
				(obs, oldVal, newVal) -> {
					inspectedSpriteView.setPositionX(Double.parseDouble(newVal));
				});
		yBox = makeDoubleInputBox("position Y", sprite.getPosition().getY(), 
				(obs, oldVal, newVal) -> {
					inspectedSpriteView.setPositionY(Double.parseDouble(newVal));
				});
		configs.getChildren().addAll(xBox, yBox);
	}
	
	private VBox makeDoubleInputBox(String title, double defaultValue, 
			ChangeListener<String> listener) {
		VBox box = new VBox();
		Label label = new Label(title);
		TextField tf = new TextField(defaultValue + "");
		box.getChildren().addAll(label, tf);
		box.setPadding(new Insets(5,5,5,5));
		tf.textProperty().addListener(listener);
		return box;
	}

}
