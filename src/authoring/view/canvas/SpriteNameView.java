package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpriteNameView extends AbstractView {
	
	private static final int RGB = 255;
	private static final double RGB_FLOAT = 255.0;
	private Label myLabel;

	public SpriteNameView(AuthoringController controller) {
		super(controller);
	}
	
	public void setName(String name) {
		long hash = name.hashCode();
		int red = (int) (hash % RGB);
		hash /= RGB;
		int green = (int) (hash % RGB);
		hash /= RGB;
		int blue = (int) (hash % RGB);
		myLabel.setTextFill(new Color(red / RGB_FLOAT, green / RGB_FLOAT, blue/ RGB_FLOAT, 1));
		myLabel.setText(name);
	}

	@Override
	protected void initUI() {
		myLabel = new Label("");
		myLabel.setFont(new Font(15));
		this.addUI(myLabel);
	}

	@Override
	protected void updateLayoutSelf() {
	}
	
}
