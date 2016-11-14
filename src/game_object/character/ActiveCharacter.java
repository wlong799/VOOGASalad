package game_object.character;

import game_object.Actor;
import game_object.DefaultConstants;

/**
 * A base class for all active characters, aka characters that can move.
 * @author Jay
 */
public abstract class ActiveCharacter extends AbstractCharacter implements Actor {

	private double myMovingUnit = DefaultConstants.MOVING_UNIT;
	private double myJumpingUnit = DefaultConstants.JUMPING_UNIT;
	
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
	public void jumpUp() {
		myPosition.setY(myPosition.getY() + myJumpingUnit);
	}

}
