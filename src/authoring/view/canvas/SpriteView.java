package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import game_object.core.ISprite;
import game_object.core.Position;

/**
 * wrapper for Sprite in AuthEnv
 */
public class SpriteView extends View {
	
	private ISprite mySprite;
	private CanvasView myCanvas;
	private SpriteImageView spImageView;
	private SpriteResizeView spResizeView;
	private Position mouseOffset;
	
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
	
	public CanvasView getCanvasView() {
		return myCanvas;
	}
	
	public void setAbsolutePositionX(double x) {
		this.getController().getCanvasViewController().setAbsolutePosition(this, x, mySprite.getPosition().getY());
	}
	
	public void setAbsolutePositionY(double y) {
		this.getController().getCanvasViewController().setAbsolutePosition(this, mySprite.getPosition().getX(), y);
	}
	
	public void setAbsolutePositionZ(double z) {
		this.getController().getCanvasViewController().setAbsolutePositionZ(this, z);
	}
	
	public Position getMouseOffset() {
		return mouseOffset;
	}
	
	/**
	 * @param width
	 * set width both frontend and backend mySprite
	 */
	public void setDimensionWidth(double width) {
		this.setWidth(width);
		mySprite.getDimension().setWidth(width);
		layout();
	}
	
	/**
	 * @param height
	 * set height both frontend and backend mySprite
	 */
	public void setDimensionHeight(double height) {
		this.setHeight(height);
		mySprite.getDimension().setHeight(height);
		layout();
	}
	
	public void indicateSelection() {
		this.removeSubView(spResizeView);
		spResizeView = new SpriteResizeView(this.getController());
		this.addSubView(spResizeView);
		layout();
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
		spImageView.setDragMove();
		mouseOffset = new Position(0, 0);
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

}
