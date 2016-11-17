package ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ComponentsView extends View {

	public ComponentsView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void layoutSelf() {
		this.clearUI();
		Rectangle rect = new Rectangle(0,0,this.getWidth(),this.getHeight());
		rect.setFill(Color.AQUA);
		this.addUI(rect);
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		
	}

}
