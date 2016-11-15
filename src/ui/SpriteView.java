package ui;

import java.util.stream.Collectors;

import game_object.ISprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	protected void initUI() {
		if (mySprite == null) return;
		String path = mySprite.getImagePaths().stream().collect(Collectors.joining(""));
		Image image = new Image(path);
		imageView = new ImageView(image);
		this.addUI(imageView);
		imageView.setOnMouseClicked(e -> 
			this.getController().selectSpriteView(this));
	}

	@Override
	protected void layoutSelf() {
		imageView.setFitWidth(this.getWidth());
		imageView.setFitHeight(this.getHeight());
	}

}
