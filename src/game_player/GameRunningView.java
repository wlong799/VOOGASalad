package game_player;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class GameRunningView {

	private Group myRoot;
	
	public GameRunningView() {
		myRoot = new Group();
	}
	
	public Group getViews() {
		return myRoot;
	}
	
	public void clearSpriteViews() {
		myRoot.getChildren().clear();
	}
	
	public void addSpriteView(ImageView image) {
		myRoot.getChildren().add(image);
	}

}
