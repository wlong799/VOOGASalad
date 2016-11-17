package ui;

import game_object.core.ISprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * wrapper for Sprite in AuthEnv
 * parent view is set to CanvasView when added to env
 */
public class SpriteView extends View {
	
	private ISprite mySprite;
	private ImageView imageView;
	
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
	
	@Override
	public void setPositionX(double x) {
		/*
		 * TODO
		 * two things:
		 * 1. set frontend UI position in display
		 * 2. set Sprite position
		 */
	}
	
	@Override
	public void setPositionY(double y) {
		//TODO
	}
	
	@Override
	protected void initUI() {
		if (mySprite == null) return;
		String path = mySprite.getImgPaths().get(0);
		Image image = new Image(path);
		imageView = new ImageView(image);
		imageView.setFitHeight(image.getHeight());
		imageView.setFitWidth(image.getWidth());
		this.addUI(imageView);
		imageView.setOnMouseClicked(e -> {
			this.getController().selectSpriteView(this);});
	}

	@Override
	protected void layoutSelf() {
		System.out.println("laying out");
	}

}
