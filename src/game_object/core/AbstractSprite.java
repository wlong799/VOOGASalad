package game_object.core;

import java.util.List;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public abstract class AbstractSprite implements ISprite {

	protected Position myPosition;
	protected Position myPreviousPosition;
	protected List<String> myImagePaths;
	protected ImageStyle myImageStyle;
	protected Dimension myDimension;

	protected AbstractSprite(Position position, Dimension dimension, List<String> imagePaths) {
		myPosition = position;
		myDimension = dimension;
		myImagePaths = imagePaths;
		myImageStyle = ImageStyle.TRUE_SIZE;
	}
	
	/* IBodyWithPosition Implementations */
	@Override
	public void setPosition(Position pos) {
		myPreviousPosition = myPosition;
		myPosition = pos;
	}
	
	@Override
	public Position getPosition() {
		return myPosition;
	}
	
	@Override
	public Position getPreviousPosition() {
		return myPreviousPosition;
	}
	
	/* IBodyWithImage Implementations */
	@Override
	public void setDimension(Dimension dimension) {
		myDimension = dimension;
	}

	@Override
	public Dimension getDimension() {
		return myDimension;
	}

	@Override
	public void setImagePaths(List<String> imagePaths) {
		myImagePaths = imagePaths;
	}
	
	@Override
	public List<String> getImagePaths() {
		return myImagePaths;
	}

	@Override
	public void setImageStyle(ImageStyle imgStyle) {
		myImageStyle = imgStyle;
	}
	
	@Override
	public ImageStyle getImgStyle() {
		return myImageStyle;
	}

	/* ICollisionBody Setter Implementations */
	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		ExceptionThrower.notYetSupported();
	}
	
	@Override
	public void setCategoryBitMask(int categoryBitMask) {
		ExceptionThrower.notYetSupported();
	}
	/* ---ICollisionBody Setter Implementations END--- */
	
	
	/* IPhysicsBody Setter Implementations */
	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		ExceptionThrower.notYetSupported();
	}
	/* ---IPhysicsBody Setter Implementations END--- */
	
	
	/* ISpriteVisualization Implementations */
	@Override
	public String getImagePath() {
		return myImagePaths.get(0);
	}
	
	@Override
	public String getImagePathLeft() {
		return myImagePaths.get(0);
	}
	
	@Override
	public String getImagePathRight() {
		if (myImagePaths.size() < 2) {
			return myImagePaths.get(0);
		}
		return myImagePaths.get(1);
	}
	
	@Override
	public boolean facingLeft() {
		return getVelocity().getXVelocity() < 0;
	}

	@Override
	public double getXForVisualization() {
		return myPosition.getX();
	}

	@Override
	public double getYForVisualization() {
		return myPosition.getY();
	}

	@Override
	public double getWidthForVisualization() {
		return myDimension.getWidth();
	}

	@Override
	public double getHeightForVisualization() {
		return myDimension.getHeight();
	}
	/* ---ISpriteVisualization Implementations END--- */
}

