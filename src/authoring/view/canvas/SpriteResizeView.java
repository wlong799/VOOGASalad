package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import authoring.constants.UIConstants;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SpriteResizeView extends View {
	
	private Line borderN;
	private Line borderW;
	private Line borderS;
	private Line borderE;
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
		borderN.setEndX(this.getWidth());
		borderW.setEndY(this.getHeight());
		borderE.setStartX(this.getWidth());
		borderE.setEndX(this.getWidth());
		borderE.setEndY(this.getHeight());
		borderS.setStartY(this.getHeight());
		borderS.setEndX(this.getWidth());
		borderS.setEndY(this.getHeight());
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
		borderN = new Line();
		borderS = new Line();
		borderW = new Line();
		borderE = new Line();
		this.addUIAll(borderN, borderS, borderW, borderE);
	}
	
	private void initResizers() {
		resizeNW = initResizerRectangle();
		resizeNE = initResizerRectangle();
		resizeSW = initResizerRectangle();
		resizeSE = initResizerRectangle();
		this.addUIAll(resizeNW, resizeNE, resizeSW, resizeSE);
		setOnHover();
		setOnDishover(resizeNW, resizeNE, resizeSW, resizeSE);
		setOnDrag();
	}
	
	private Rectangle initResizerRectangle() {
		Rectangle result = new Rectangle(0, 0, resize_unit, resize_unit);
		result.setStrokeWidth(UIConstants.RESIZER_STROKE_WIDTH);
		result.setStroke(Color.TRANSPARENT);
		return result;
	}
	
	private void setOnHover() {
		resizeNW.setOnMouseEntered(e -> {
			this.getController().setMouseCursor(Cursor.NW_RESIZE);
		});
		resizeNE.setOnMouseEntered(e -> {
			this.getController().setMouseCursor(Cursor.NE_RESIZE);
		});
		resizeSW.setOnMouseEntered(e -> {
			this.getController().setMouseCursor(Cursor.SW_RESIZE);
		});
		resizeSE.setOnMouseEntered(e -> {
			this.getController().setMouseCursor(Cursor.SE_RESIZE);
		});
	}
	
	private void setOnDishover(Rectangle...resizers) {
		for (Rectangle resizer : resizers) {
			resizer.setOnMouseExited(e -> {
				this.getController().setMouseCursor(Cursor.DEFAULT);
			});
		}
	}
	
	private void setOnDrag() {
		CanvasView canvas = spView.getCanvasView();
		resizeSE.setOnMouseDragged(e -> {
			canvas.onResizeSpriteView(
					spView, 
					spView.getPositionX(),
					spView.getPositionY(),
					e.getSceneX() - canvas.getPositionX(),
					e.getSceneY() - canvas.getPositionY());
		});
		resizeSW.setOnMouseDragged(e -> {
			canvas.onResizeSpriteView(
					spView, 
					e.getSceneX() - canvas.getPositionX(),
					spView.getPositionY(),
					spView.getPositionX() + spView.getWidth(),
					e.getSceneY() - canvas.getPositionY());
		});
		resizeNE.setOnMouseDragged(e -> {
			canvas.onResizeSpriteView(
					spView, 
					spView.getPositionX(),
					e.getSceneY() - canvas.getPositionY(),
					e.getSceneX() - canvas.getPositionX(),
					spView.getPositionY() + spView.getHeight());
		});
		resizeNW.setOnMouseDragged(e -> {
			canvas.onResizeSpriteView(
					spView, 
					e.getSceneX() - canvas.getPositionX(),
					e.getSceneY() - canvas.getPositionY(),
					spView.getPositionX() + spView.getWidth(),
					spView.getPositionY() + spView.getHeight());
		});
	}

}
