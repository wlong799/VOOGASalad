package ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NavigatorView extends View {

	public NavigatorView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void layoutSelf() {
		this.clearUI();
		Rectangle rect = new Rectangle(0,0,this.getWidth(),this.getHeight());
		rect.setFill(Color.BLUE);
		this.addUI(rect);
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		
	}

}
