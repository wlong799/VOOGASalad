package game_object.character;

import java.util.List;

import game_object.core.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.Position;
import game_object.core.Velocity;

/**
 * A base class for all active characters, aka characters that can move.
 * @author Jay
 */
abstract class ActiveCharacter extends AbstractCharacter implements ITriggerable {

	private double myMovingUnit = DefaultConstants.MOVING_UNIT;
	private double myJumpingUnit = DefaultConstants.JUMPING_UNIT;
	private Velocity myVelocity = new Velocity(0, 0);
	
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

	/* IMover Implementations */
	@Override
	public void moveRight() {
		myVelocity.setXVelocity(myMovingUnit);
	}
	
	@Override
	public void moveLeft() {
		myVelocity.setXVelocity(-myMovingUnit);
	}

	@Override
	public void moveUp() {
		ExceptionThrower.notYetSupported();
	}

	@Override
	public void moveDown() {
		ExceptionThrower.notYetSupported();
	}

	@Override
	public void jumpUp() { // jumping is simulated by given the sprite a upward (negative) velocity.
		myVelocity.setYVelocity(-myJumpingUnit);
	}
	/* ---IMover Implementations END---*/
	
	
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
