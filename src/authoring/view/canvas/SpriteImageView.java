package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import game_object.core.Dimension;
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
		Image image = new Image(path);
		imageView = new ImageView(image);
		
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
		layout();
		
		mySprite.setDimension(new Dimension());
		mySprite.getDimension().setWidth(image.getWidth());
		mySprite.getDimension().setHeight(image.getHeight());
	}

}
