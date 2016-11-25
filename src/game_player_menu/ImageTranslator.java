package game_player_menu;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTranslator extends NodeTranslator {

	
	@Override
	public Node createNode(String text){
		Image currImage = new Image(getClass().getResourceAsStream(text));
		return new ImageView(currImage);
	}
	
	

}