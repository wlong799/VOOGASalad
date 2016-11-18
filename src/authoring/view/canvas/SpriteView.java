package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import game_object.core.ISprite;

/**
 * wrapper for Sprite in AuthEnv
 */
public class SpriteView extends View {
	
	//TODO: extract selection indicator to a view
	//so that they can be laid out automatically
	private ISprite mySprite;
	private CanvasView myCanvas;
	private SpriteImageView spImageView;
	private SpriteResizeView spResizeView;
	
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
		this.setWidth(width);
		mySprite.getDimension().setWidth(width);
		layout();
	}
	
	public void setDimensionHeight(double height) {
		this.setHeight(height);
		mySprite.getDimension().setHeight(height);
		layout();
	}
	
	public void indicateSelection() {
		this.removeSubView(spResizeView);
		spResizeView = new SpriteResizeView(this.getController());
		this.addSubView(spResizeView);
	}
	
	public void indicateDeselection() {
		this.removeSubView(spResizeView);
	}
	
	@Override
	protected void initUI() {
		if (mySprite == null) return;
		
		spImageView = new SpriteImageView(this.getController());
		this.addSubView(spImageView);
		this.setHeight(spImageView.getHeight());
		this.setWidth(spImageView.getWidth());
		
		setMouseClicked();
		setDragMove();
	}

	@Override
	protected void layoutSelf() {
		spImageView.setWidth(this.getWidth());
		spImageView.setHeight(this.getHeight());
		spResizeView.setWidth(this.getWidth());
		spResizeView.setHeight(this.getHeight());
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

}
