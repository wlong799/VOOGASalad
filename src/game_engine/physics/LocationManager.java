package game_engine.physics;

import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.AbstractCharacter;
import game_object.core.AbstractSprite;
import game_object.simulation.IPhysicsBody;

/**
 * @author Charlie Wang
 */
public class LocationManager {
	Gravity myGravity;

	public LocationManager() {
		myGravity = new Gravity();
	}

	public void setGroundBlocks(List<AbstractBlock> blocks) {
		myGravity.setGroundBlocks(blocks);
	}

	public double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime) {
		double y = body.getPosition().getY();
		double vy = calculateNewVerticalVelocity(body, elapsedTime);
		double newy = y + elapsedTime * vy;
		return newy;
	}

	public double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime) {
		//TODO: add velocity to all bodies
		if (body instanceof AbstractCharacter) {
			double vy = ((AbstractCharacter) body).getVelocity().getYVelocity();
			double newvy = vy + elapsedTime * myGravity.getGravity();
			return newvy;
		}
		return 0;
	}

	public double calculateNewHorizontalPosition(AbstractSprite sprite, double elapsedTime) {
		return elapsedTime;
	}

	public double calculateNewHorizontalVelocity(AbstractSprite sprite, double elapsedTime) {
		return elapsedTime;
	}

	public boolean checkTouchedGround(AbstractCharacter character, double newy) {
		// TODO: loop through all the (can-stand-on) blocks can check if they
		// collide with the top border of the hit box
		return false;
	}

}
