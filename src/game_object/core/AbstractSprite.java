package game_object.core;

import java.util.List;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public abstract class AbstractSprite implements ISprite {

	private static Position staticPivotPosition;
	protected Position myPosition;
	protected Position myPreviousPosition;
	protected List<String> myImagePaths;
	protected ImageStyle myImageStyle;
	protected Dimension myDimension;

	static {
		staticPivotPosition = new Position(0, 0);
	}
	
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
	public static Position getStaticPivotPosition() {
		return staticPivotPosition;
	}
	
	@Override
	public String getImagePath() {
		return getVelocity().getXVelocity() < 0 // face left
				? myImagePaths.get(0)
				: (
					myImagePaths.size() < 2
					? myImagePaths.get(0)
					: myImagePaths.get(1)
				);
	}

	@Override
	public double getXForVisualization() {
		return myPosition.getX() - staticPivotPosition.getX()
			+ 200;
	}

	@Override
	public double getYForVisualization() {
		return myPosition.getY();// - staticPivotPosition.getY()
			//+ Screen.getPrimary().getVisualBounds().getHeight() / 2;
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

