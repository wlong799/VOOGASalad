package authoring.view;

import authoring.AuthoringController;
import authoring.View;
import game_object.core.Dimension;
import game_object.core.ISprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * wrapper for Sprite in AuthEnv
 */
public class SpriteView extends View {
	
	private ISprite mySprite;
	private ImageView imageView;
	private CanvasView myCanvas;
	
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
		imageView.setFitWidth(width);
		mySprite.getDimension().setWidth(width);
	}
	
	public void setDimensionHeight(double height) {
		imageView.setFitHeight(height);
		mySprite.getDimension().setHeight(height);
	}
	
	@Override
	public double getWidth() {
		return imageView.getFitWidth();
	}
	
	@Override
	public double getHeight() {
		return imageView.getFitHeight();
	}
	
	@Override
	protected void initUI() {
		if (mySprite == null) return;
		String path = mySprite.getImgPaths().get(0);
		Image image = new Image(path);
		imageView = new ImageView(image);
		imageView.setFitHeight(image.getHeight());
		imageView.setFitWidth(image.getWidth());
		mySprite.setDimension(new Dimension());
		mySprite.getDimension().setWidth(image.getWidth());
		mySprite.getDimension().setHeight(image.getHeight());
		this.addUI(imageView);
		setMouseClicked();
		setDragMove();
	}

	@Override
	protected void layoutSelf() {
	}
	
	private void setMouseClicked() {
		imageView.setOnMouseClicked(e -> {
			this.getController().selectSpriteView(this);
		});
	}
	
	private void setDragMove() {
		imageView.setOnMouseDragged(event -> {
			myCanvas.onDragSpriteView(this, event);
		});
	}

}
