package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import game_object.core.Dimension;
import game_object.core.ISprite;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * wrapper for Sprite in AuthEnv
 */
public class SpriteView extends View {
	
	//TODO: extract selection indicator to a view
	//so that they can be laid out automatically
	private ISprite mySprite;
	private Group plate;
	private ImageView imageView;
	private CanvasView myCanvas;
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
		imageView.setFitWidth(width);
		mySprite.getDimension().setWidth(width);
		redrawSelection();
	}
	
	public void setDimensionHeight(double height) {
		imageView.setFitHeight(height);
		mySprite.getDimension().setHeight(height);
		redrawSelection();
	}
	
	@Override
	public double getWidth() {
		return imageView.getFitWidth();
	}
	
	@Override
	public double getHeight() {
		return imageView.getFitHeight();
	}
	
	public void indicateSelection() {
		selected = true;
		selectionIndicator = initSelectionIndicator();
		plate.getChildren().clear();
		plate.getChildren().addAll(selectionIndicator, imageView);
	}
	
	public void indicateDeselection() {
		selected = false;
		plate.getChildren().remove(selectionIndicator);
	}
	
	@Override
	protected void initUI() {
		if (mySprite == null) return;
		plate = new Group();
		String path = mySprite.getImagePaths().get(0);
		initImageAndSprite(path);
		
		plate.getChildren().add(imageView);
		this.addUI(plate);
		
		setMouseClicked();
		setDragMove();
	}

	@Override
	protected void layoutSelf() {
	}
	
	private void setMouseClicked() {
		plate.setOnMouseClicked(e -> {
			this.getController().selectSpriteView(this);
		});
	}
	
	private void setDragMove() {
		plate.setOnMouseDragged(event -> {
			myCanvas.onDragSpriteView(this, event);
		});
	}
	
	private void initImageAndSprite(String path) {
		Image image = new Image(path);
		imageView = new ImageView(image);
		
		imageView.setFitHeight(image.getHeight());
		imageView.setFitWidth(image.getWidth());
		
		mySprite.setDimension(new Dimension());
		mySprite.getDimension().setWidth(image.getWidth());
		mySprite.getDimension().setHeight(image.getHeight());
	}
	
	private Rectangle initSelectionIndicator() {
		Rectangle border =  new Rectangle(
				0,
				0,
				imageView.getFitWidth(),
				imageView.getFitHeight());
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
