package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpriteResizeView extends View {
	
	private Rectangle border;
	private Rectangle resizeNW;
	private Rectangle resizeNE;
	private Rectangle resizeSW;
	private Rectangle resizeSE;
	private SpriteView spView;

	public SpriteResizeView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	public void setParentView(View parent) {
		spView = (SpriteView) parent;
		initUI();
	}

	@Override
	protected void initUI() {
		if (spView == null) return;
		initSelectionIndicator();
	}

	@Override
	protected void layoutSelf() {
		border.setWidth(this.getWidth());
		border.setHeight(this.getHeight());
	}
	
	private void initSelectionIndicator() {
		border =  new Rectangle(
				0,
				0,
				spView.getWidth(),
				spView.getHeight());
		border.setFill(Color.TRANSPARENT);
		border.setStroke(Color.BLACK);
		this.addUI(border);
	}

}
