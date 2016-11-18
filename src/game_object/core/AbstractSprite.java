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
	protected boolean myAffectedByPhysics;
	protected Velocity myVelocity;
	
	protected AbstractSprite(double x, double y, List<String> imgPaths) {
		this(new Position(x, y), imgPaths);
	}

	protected AbstractSprite(Position position, List<String> imgPaths) {
		this(imgPaths);
		myPosition = position;
	}

	private AbstractSprite(List<String> imagePaths) {
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
	
	/* IPhysicsBody Setter Implementations */
	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		ExceptionThrower.notYetSupported();
	}
}

