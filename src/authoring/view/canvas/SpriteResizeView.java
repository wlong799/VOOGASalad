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
	private static final double resize_unit = 5;

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
		resizeNW.setLayoutX(-resize_unit / 2);
		resizeNW.setLayoutY(-resize_unit / 2);
		resizeNE.setLayoutX(this.getWidth() - resize_unit / 2);
		resizeNE.setLayoutY(-resize_unit / 2);
		resizeSW.setLayoutX(-resize_unit / 2);
		resizeSW.setLayoutY(this.getHeight() - resize_unit / 2);
		resizeSE.setLayoutX(this.getWidth() - resize_unit / 2);
		resizeSE.setLayoutY(this.getHeight() - resize_unit / 2);
	}
	
	private void initSelectionIndicator() {
		initBorder();
		initResizers();
	}

	private void initBorder() {
		border =  new Rectangle(
				0,
				0,
				spView.getWidth(),
				spView.getHeight());
		border.setFill(Color.TRANSPARENT);
		border.setStroke(Color.BLACK);
		this.addUI(border);
	}
	
	private void initResizers() {
		resizeNW = new Rectangle(0, 0, resize_unit, resize_unit);
		resizeNE = new Rectangle(0, 0, resize_unit, resize_unit);
		resizeSW = new Rectangle(0, 0, resize_unit, resize_unit);
		resizeSE = new Rectangle(0, 0, resize_unit, resize_unit);
		this.addUIAll(resizeNW, resizeNE, resizeSW, resizeSE);
	}

}
