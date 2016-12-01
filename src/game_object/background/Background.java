package game_object.background;

import java.util.ArrayList;

/**
 * This represents the background scene of the game
 * @author Yilun
 *
 */
public class Background {

	private ArrayList<String> myImagePaths;

	public ArrayList<String> getImgPaths() {
		return myImagePaths;
	}

	public void setImgPaths(ArrayList<String> imgPaths) {
		this.myImagePaths = imgPaths;
	}
	
}
