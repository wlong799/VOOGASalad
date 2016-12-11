package game_object.core;

import java.util.List;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_engine.physics.ConstantStrategy;
import game_engine.physics.IPhysicsStrategy;
import game_object.block.Block;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.constants.DefaultConstants;
import game_object.level.SpriteScavenger;
import game_object.powerup.IPowerUp;
import game_object.simulation.ICollisionBody;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public abstract class AbstractSprite implements ISprite {

	private static final long serialVersionUID = -5935430491986661297L;
	private static SpriteScavenger staticSpriteScavenger;
	protected Position myPosition;
	protected Position myPreviousPosition;
	protected List<String> myImagePaths;
	protected ImageStyle myImageStyle;
	protected Dimension myDimension;
	protected boolean myValid;
	protected int myCategoryBitMask;
	protected int myCollisionBitMask;
	protected boolean myAffectedByPhysics;
	protected Velocity myVelocity;
	protected Velocity myPreviousVelocity;
	protected IPhysicsStrategy myPhysicsStrategy;
	protected boolean myFacingLeft;
	protected ChildSprites myChildSprites;
	
	static {
		staticPivotPosition = new Position(0, 0);
		staticSpriteScavenger = new SpriteScavenger();
	}
	
	protected AbstractSprite(Position position, Dimension dimension, List<String> imagePaths) {
		myPosition = position;
		myDimension = dimension;
		myImagePaths = imagePaths;
		myImageStyle = ImageStyle.FIT;
		myCategoryBitMask = DefaultConstants.VOID_CATEGORY_BIT_MASK;
		myCollisionBitMask = DefaultConstants.VOID_CATEGORY_BIT_MASK;
		myAffectedByPhysics = false;
		myVelocity = new Velocity(0, 0);
		myPhysicsStrategy = new ConstantStrategy();
		myValid = true;
		myFacingLeft = false; //default to face right.
		myChildSprites = new ChildSprites();
	}
	
	/* General Setting */
	public static SpriteScavenger getSpriteScavenger() {
		return staticSpriteScavenger;
	}
	
	@Override
	public void setValid(boolean valid) {
		myValid = valid;
	}
	
	@Override
	public boolean isValid() {
		return myValid;
	}
	
	@Override
	public boolean isFacingLeft() {
		if (getVelocity() != null && getVelocity().getXVelocity() != 0) {
			myFacingLeft = getVelocity().getXVelocity() < 0;
		}
		return myFacingLeft;
	}
	
	public ChildSprites getChildSprites() {
		return myChildSprites;
	}
	/* ---General Setting END--- */
	
	/* IBodyWithPosition Implementations */
	@Override
	public void setPosition(Position pos) {
		myPosition = pos;
	}
	
	@Override
	public Position getPosition() {
		return myPosition;
	}
	
	@Override
	public void setPreviousPosition(Position previousPosition) {
		myPreviousPosition = previousPosition;
	}
	
	@Override
	public Position getPreviousPosition() {
		return myPreviousPosition == null ? getPosition() : myPreviousPosition;
	}
	/* ---IBodyWithPosition Implementations END--- */
	
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
	/* ---IBodyWithImage Implementations END--- */

	
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
	
	@Override
	public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection) {
		
	}
	
	@Override
	public void onCollideWith(Hero h, CollisionDirection collisionDirection){
		
	}
	
	@Override
	public void onCollideWith(Enemy e, CollisionDirection collisionDirection){
		
	}
	
	@Override
	public void onCollideWith(Block b, CollisionDirection collisionDirection){
	    
	}
	
	@Override
    public void onCollideWith(IPowerUp p, CollisionDirection collisionDirection){
       
    }
	/* ---ICollisionBody Implementations END--- */
	
	
	/* IPhysicsBody Setter Implementations */
	
	@Override
	public IPhysicsStrategy getPhysics(){
	    return myPhysicsStrategy;
	}
	
	@Override
	public void setPhysics(IPhysicsStrategy physics){
	    myPhysicsStrategy = physics;
	}
	
	@Override
	public boolean getAffectedByPhysics() {
		return myAffectedByPhysics;
	}
	
	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		myAffectedByPhysics = affectedByPhysics;
	}
	
	@Override
	public Velocity getVelocity() {
		return myVelocity;
	}

	@Override
	public void setVelocity(Velocity velocity) {
		myVelocity = velocity;
	}
	
	@Override
	public Velocity getPreviousVelocity() {
		return myPreviousVelocity == null ? getVelocity() : myPreviousVelocity;
	}
	
	@Override
	public void setPreviousVelocity(Velocity previousVelocity) {
		myPreviousVelocity = previousVelocity;
	}
	/* ---IPhysicsBody Setter Implementations END--- */
	
	
	/* ISpriteVisualization Implementations */
	private static Position staticPivotPosition;
	private static Dimension staticPivotDimension;
	private static double X_SCROLL_THRESHOLD = DefaultConstants.X_SCROLL_THRESHOLD;
	private static double Y_SCROLL_PERCENT = DefaultConstants.Y_SCROLL_PERCENT;
	
	private double myScrollOffset = 0;
	
	public static Position getStaticPivotPosition() {
		return staticPivotPosition;
	}
	
	public static void setStaticPivotDimension(Dimension pivotDimension) {
		staticPivotDimension = pivotDimension;
	}
	
	@Override
	public String getImagePath() {
		return isFacingLeft() // face left
			? myImagePaths.get(0)
			: (
				myImagePaths.size() < 2
				? myImagePaths.get(0)
				: myImagePaths.get(1)
			);
	}

	@Override
	public double getXForVisualization() {
		double staticX = staticPivotPosition.getX();
		double myX = myPosition.getX();
		if (staticX + myScrollOffset < X_SCROLL_THRESHOLD) {
			myScrollOffset += X_SCROLL_THRESHOLD - staticX - myScrollOffset;
		} else if (staticX + myScrollOffset > staticPivotDimension.getWidth() - X_SCROLL_THRESHOLD) {
			myScrollOffset -= staticX + myScrollOffset - staticPivotDimension.getWidth() + X_SCROLL_THRESHOLD;
		}
		return myX + myScrollOffset;
	}

	@Override
	public double getYForVisualization() {
		return myPosition.getY() - staticPivotPosition.getY() 
				+ Y_SCROLL_PERCENT * staticPivotDimension.getHeight();
	}

	@Override
	public double getWidthForVisualization() {
		return myDimension.getWidth();
	}

	@Override
	public double getHeightForVisualization() {
		return myDimension.getHeight();
	}
	
	@Override
	public double getScrollOffset() {
		return myScrollOffset;
	}
	
	protected void setScrollOffset(double offset) {
		myScrollOffset = offset;
	}
	
	/* ---ISpriteVisualization Implementations END--- */
}

