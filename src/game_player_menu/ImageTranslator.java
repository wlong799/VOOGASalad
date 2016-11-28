package game_player_menu;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTranslator extends NodeTranslator {

	
	/*public ImageTranslator(ISelectable listener) {
		super(listener);
		// TODO Auto-generated constructor stub
	}
*/
	@Override
	public Node createNode(String text, boolean isSelectable, ISelectable listener){
		Image currImage = new Image(getClass().getResourceAsStream(text));
		ImageView view = new ImageView(currImage);
		view.setFitWidth(Double.parseDouble(myResources.getString("ItemDescriptionImageWidth")));
		view.setFitHeight(Double.parseDouble(myResources.getString("ItemDescriptionImageHeight")));
		if(isSelectable){
			makeImageViewSelectable(view, listener);
		}
		return view;
	}

	private void makeImageViewSelectable(ImageView view, ISelectable listener) {
		view.setOnMouseClicked(e -> listener.select());
	}
	
	

}