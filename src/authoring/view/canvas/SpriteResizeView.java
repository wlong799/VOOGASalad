package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
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
	}

	@Override
	protected void layoutSelf() {
		// TODO Auto-generated method stub
		
	}

}
