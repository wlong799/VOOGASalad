package game_object.background;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents the background scene of the game
 * @author Yilun, Will
 *
 */
public class Background {
	private List<String> myImagePaths;

	public Background() {
		myImagePaths = new ArrayList<>();
	}
	public List<String> getImagePaths() {
		return myImagePaths;
	}

	public void appendImagePath(String imagePath) {
		myImagePaths.add(imagePath);
	}

	public void clearImagePaths() {
		myImagePaths.clear();
	}
	
}
