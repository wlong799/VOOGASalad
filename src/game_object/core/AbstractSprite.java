package game_object.core;

import java.util.ArrayList;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public abstract class AbstractSprite {

	protected Position myPosition;
	protected ArrayList<String> myImgPaths;
	protected ImageStyle myImgStyle;
	
	
	public AbstractSprite(double x, double y, ArrayList<String> imgPaths) {
		myPosition = new Position(x, y);
		myImgPaths = imgPaths;
		myImgStyle = ImageStyle.TRUE_SIZE;
	}

	public AbstractSprite(Position position, ArrayList<String> imgPaths) {
		myPosition = position;
		myImgPaths = imgPaths;
		myImgStyle = ImageStyle.TRUE_SIZE;
	}

	public Position getPosition() {
		return myPosition;
	}
	
	public void setPosition(Position pos) {
		myPosition = pos;
	}

	public ArrayList<String> getImgPaths() {
		return myImgPaths;
	}

	public void setImgPaths(ArrayList<String> imgPaths) {
		myImgPaths = imgPaths;
	}

	public ImageStyle getImgStyle() {
		return myImgStyle;
	}

	public void setImgStyle(ImageStyle imgStyle) {
		myImgStyle = imgStyle;
	}

	
}
