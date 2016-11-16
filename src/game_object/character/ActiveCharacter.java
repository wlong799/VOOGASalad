package game_object.character;

import java.util.ArrayList;

import game_object.core.DefaultConstants;

/**
 * A base class for all active characters, aka characters that can move.
 * @author Jay
 */
public abstract class ActiveCharacter extends AbstractCharacter implements IMover {

	public ActiveCharacter(double x, double y, ArrayList<String> imgPaths, double maxHP) {
		super(x, y, imgPaths, maxHP);
	}

	private double myMovingUnit = DefaultConstants.MOVING_UNIT;
	private double myJumpingUnit = DefaultConstants.JUMPING_UNIT;
	
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

}
