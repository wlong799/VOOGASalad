package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import game_object.core.ISprite;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * wrapper for Sprite in AuthEnv
 */
public class SpriteView extends View {
	
	//TODO: extract selection indicator to a view
	//so that they can be laid out automatically
	private ISprite mySprite;
	private CanvasView myCanvas;
	private SpriteImageView spImageView;
	private Rectangle selectionIndicator;
	private boolean selected;
	
	public SpriteView(AuthoringController controller) {
		super(controller);
	}
	
	public void setSprite(ISprite sprite) {
		mySprite = sprite;
		initUI();
	}
	
	public ISprite getSprite() {
		return mySprite;
	}
	
	public void setCanvasView(CanvasView canvas) {
		myCanvas = canvas;
	}
	
	public void setAbsolutePositionX(double x) {
		myCanvas.setAbsolutePosition(this, x, mySprite.getPosition().getY());
	}
	
	public void setAbsolutePositionY(double y) {
		myCanvas.setAbsolutePosition(this, mySprite.getPosition().getX(), y);
	}
	
	public void setRelativePositionX(double x) {
		myCanvas.setRelativePosition(this, x, mySprite.getPosition().getY());
	}
	
	public void setRelativePositionY(double y) {
		myCanvas.setRelativePosition(this, mySprite.getPosition().getX(), y);
	}
	
	public void setDimensionWidth(double width) {
		spImageView.setWidth(width);
		mySprite.getDimension().setWidth(width);
		redrawSelection();
	}
	
	public void setDimensionHeight(double height) {
		spImageView.setHeight(height);
		mySprite.getDimension().setHeight(height);
		redrawSelection();
	}
	
	@Override
	public double getWidth() {
		return spImageView.getWidth();
	}
	
	@Override
	public double getHeight() {
		return spImageView.getHeight();
	}
	
	public void indicateSelection() {
		selected = true;
		selectionIndicator = initSelectionIndicator();
		this.clearUI();
		this.addUIAll(selectionIndicator, spImageView.getUI());
	}
	
	public void indicateDeselection() {
		selected = false;
		this.clearUI();
		this.addUI(spImageView.getUI());
	}
	
	@Override
	protected void initUI() {
		if (mySprite == null) return;
		
		spImageView = new SpriteImageView(this.getController());
		this.addSubView(spImageView);
		
		setMouseClicked();
		setDragMove();
	}

	@Override
	protected void layoutSelf() {
		spImageView.setWidth(this.getWidth());
		spImageView.setHeight(this.getHeight());
	}
	
	private void setMouseClicked() {
		this.getUI().setOnMouseClicked(e -> {
			this.getController().selectSpriteView(this);
		});
	}
	
	private void setDragMove() {
		//TODO: save offset
		this.getUI().setOnMouseDragged(event -> {
			myCanvas.onDragSpriteView(this, event);
		});
	}
	
	private Rectangle initSelectionIndicator() {
		Rectangle border =  new Rectangle(
				0,
				0,
				spImageView.getWidth(),
				spImageView.getHeight());
		border.setFill(Color.TRANSPARENT);
		border.setStroke(Color.BLACK);
		return border;
	}
	
	private void redrawSelection() {
		if (selected) {
			indicateDeselection();
			indicateSelection();
		}
	}

}
