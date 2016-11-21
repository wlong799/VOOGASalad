package authoring.view.canvas;

import java.io.File;

import authoring.AuthoringController;
import authoring.View;
import game_object.core.ISprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteImageView extends View {
	
	private SpriteView spView;
	private ImageView imageView;
	private ISprite mySprite;

	public SpriteImageView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	public void setParentView(View parent) {
		spView = (SpriteView) parent;
		mySprite = spView.getSprite();
		initUI();
	}

	@Override
	protected void initUI() {
		if (spView == null) return;
		String path = mySprite.getImagePaths().get(0);
		initImageAndSprite(path);
		this.addUI(imageView);
	}

	@Override
	protected void layoutSelf() {
		imageView.setFitHeight(this.getHeight());
		imageView.setFitWidth(this.getWidth());
	}
	
	private void initImageAndSprite(String path) {
		Image image = new Image(new File(path).toURI().toString());
		imageView = new ImageView(image);
		
		
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
		layout();
		
		mySprite.getDimension().setWidth(image.getWidth());
		mySprite.getDimension().setHeight(image.getHeight());
	}
	
	public void setDragMove() {
		this.getUI().setOnMousePressed(event -> {
			CanvasView canvas = spView.getCanvasView();
			spView.getMouseOffset().setX(
				this.getController().getCanvasViewController()
				.toAbsoluteX(event.getSceneX() - canvas.getPositionX()) - spView.getPositionX());
			spView.getMouseOffset().setY(
				this.getController().getCanvasViewController()
				.toAbsoluteY(event.getSceneY() - canvas.getPositionY()) - spView.getPositionY());
		});
		this.getUI().setOnMouseDragged(event -> {
			this.getController().getCanvasViewController().onDragSpriteView(spView, event);
		});
	}

}
