package game_object.character;

import java.util.List;

import game_object.core.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;

/**
 * A base class for all active characters, aka characters that can move.
 * @author Jay
 */
abstract class ActiveCharacter extends AbstractCharacter {

	private double myMovingUnit = DefaultConstants.MOVING_UNIT;
	private double myJumpingUnit = DefaultConstants.JUMPING_UNIT;
	private Velocity myVelocity;
	
	protected ActiveCharacter(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
	}
	
	public double getMovingUnit() {
		return myMovingUnit;
	}

	public void setMovingUnit(double movingUnit) {
		myMovingUnit = movingUnit;
	}

	public double getJumpingUnit() {
		return myJumpingUnit;
	}

	public void setJumpingUnit(double jumpingUnit) {
		myJumpingUnit = jumpingUnit;
	}

	@Override
	public void moveRight() {
		myPosition.setX(myPosition.getX() + myMovingUnit);
	}
	
	@Override
	public void moveLeft() {
		myPosition.setX(myPosition.getX() - myMovingUnit);
	}

	@Override
	public void moveUp() {
		myPosition.setY(myPosition.getY() - myMovingUnit);
	}

	@Override
	public void moveDown() {
		myPosition.setY(myPosition.getY() + myMovingUnit);
	}

	@Override
	public void jumpUp() { // jumping is simulated by given the sprite a upward (negative) velocity.
		myVelocity.setYVelocity(myJumpingUnit);
	}
	
	/* IPhysicsBody Implementations */	
	@Override
	public boolean getAffectedByPhysics() {
		return true;
	}
	
	@Override
	public void setVelocity(Velocity velocity) {
		myVelocity = velocity;
	}
	
	@Override
	public Velocity getVelocity() {
		return myVelocity;
	}
	/* ---IPhysicsBody Implementations END--- */	
}
