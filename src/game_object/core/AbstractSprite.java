package game_object.core;

import java.util.List;

import game_object.constants.DefaultConstants;

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
	protected int myCategoryBitMask;
	protected int myCollisionBitMask;
	protected boolean myAffectedByPhysics;
	
	static {
		staticPivotPosition = new Position(0, 0);
	}
	
	public AbstractSprite(Position position, Dimension dimension, List<String> imagePaths) {
		myPosition = position;
		myDimension = dimension;
		myImagePaths = imagePaths;
		myImageStyle = ImageStyle.FIT;
		myCategoryBitMask = DefaultConstants.VOID_CATEGORY_BIT_MASK;
		myCollisionBitMask = DefaultConstants.VOID_CATEGORY_BIT_MASK;
		myAffectedByPhysics = false;
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
	public ImageStyle getImageStyle() {
		return myImageStyle;
	}

	/* ICollisionBody Implementations */
	@Override
	public void setCategoryBitMask(int categoryBitMask) {
		myCategoryBitMask = categoryBitMask;
	}
	
	@Override
	public int getCategoryBitMask() {
		return myCategoryBitMask;
	}

	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		myCollisionBitMask = collisionBitMask;
	}
	
	@Override
	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}
	/* ---ICollisionBody Implementations END--- */
	
	
	/* IPhysicsBody Setter Implementations */
	@Override
	public boolean getAffectedByPhysics() {
		return myAffectedByPhysics;
	}
	
	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		myAffectedByPhysics = affectedByPhysics;
	}
	/* ---IPhysicsBody Setter Implementations END--- */
	
	
	/* ISpriteVisualization Implementations */
	private String myPreviousImagePath;
	private static Position staticPivotPosition;
	private static Dimension staticPivotDimension;
	private double offset = 0;
	
	public static Position getStaticPivotPosition() {
		return staticPivotPosition;
	}
	
	public static void setStaticPivotDimension(Dimension pivotDimension) {
		staticPivotDimension = pivotDimension;
	}
	
	@Override
	public String getImagePath() {
		if (getVelocity() != null && getVelocity().getXVelocity() != 0) {
			myPreviousImagePath = getVelocity().getXVelocity() < 0 // face left
				? myImagePaths.get(0)
				: (
					myImagePaths.size() < 2
					? myImagePaths.get(0)
					: myImagePaths.get(1)
				);
		} else {
			if (myPreviousImagePath == null) {
				myPreviousImagePath = myImagePaths.get(0);
			}
		}
		return myPreviousImagePath;
	}

	@Override
	public double getXForVisualization() {
		double staticX = staticPivotPosition.getX();
		double myX = myPosition.getX();
		double threshold = DefaultConstants.SCROLL_THRESHOLD;
		if (staticX + offset < threshold) {
			offset += threshold - staticX - offset;
		}
		else if (staticX + offset > staticPivotDimension.getWidth() - threshold) {
			offset -= staticX + offset - staticPivotDimension.getWidth() + threshold;
		}
		return myX + offset;
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

