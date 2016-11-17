package game_object.core;

import java.util.List;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public abstract class AbstractSprite implements ISprite {

	protected Position myPosition;
	protected List<String> myImgPaths;
	protected ImageStyle myImgStyle;
	
	
	protected AbstractSprite(double x, double y, List<String> imgPaths) {
		this(imgPaths);
		myPosition = new Position(x, y);
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
	public void setPosition(Position pos) {
		myPosition = pos;
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

	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		ExceptionThrower.notYetSupported();
	}

	@Override
	public boolean getAffectedByPhysics() {
		ExceptionThrower.needToBeOverriden();
		return false;
	}

	@Override
	public Velocity getVelocity() {
		ExceptionThrower.needToBeOverriden();
		return null;
	}

	@Override
	public void setVelocity(Velocity velocity) {
		ExceptionThrower.needToBeOverriden();
	}

	@Override
	public void setCategoryBitMask(int categoryBitMask) {
		ExceptionThrower.needToBeOverriden();
	}

	@Override
	public int getCategoryBitMask() {
		ExceptionThrower.needToBeOverriden();
		return 0;
	}

	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		ExceptionThrower.needToBeOverriden();
	}

	@Override
	public int getCollisionBitMask() {
		ExceptionThrower.needToBeOverriden();
		return 0;
	}

	
}
