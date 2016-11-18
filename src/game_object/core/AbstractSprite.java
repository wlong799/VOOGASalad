package game_object.core;

import java.util.List;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public abstract class AbstractSprite implements ISprite {

	protected Position myPosition;
	protected Position myPreviousPosition;
	protected List<String> myImgPaths;
	protected ImageStyle myImgStyle;
	protected Dimension myDimension;
	
	protected AbstractSprite(double x, double y, List<String> imgPaths) {
		this(new Position(x, y), imgPaths);
	}

	protected AbstractSprite(Position position, List<String> imgPaths) {
		this(imgPaths);
		myPosition = position;
	}

	private AbstractSprite(List<String> imgPaths) {
		myImgPaths = imgPaths;
		myImgStyle = ImageStyle.TRUE_SIZE;
	}
	
	@Override
	public Position getPosition() {
		return myPosition;
	}
	
	@Override
	public Position getPreviousPosition() {
		return myPreviousPosition;
	}
	
	@Override
	public void setPosition(Position pos) {
		myPreviousPosition = myPosition;
		myPosition = pos;
	}
	
	@Override
	public void setDimension(Dimension dimension) {
		myDimension = dimension;
	}

	@Override
	public Dimension getDimension() {
		return myDimension;
	}

	@Override
	public List<String> getImgPaths() {
		return myImgPaths;
	}

	@Override
	public void setImgPaths(List<String> imgPaths) {
		myImgPaths = imgPaths;
	}

	@Override
	public ImageStyle getImgStyle() {
		return myImgStyle;
	}

	@Override
	public void setImgStyle(ImageStyle imgStyle) {
		myImgStyle = imgStyle;
	}
	
}
