package game_engine.physics;

import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.AbstractCharacter;
import game_object.core.AbstractSprite;

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

	public double calculateNewVerticalPosition(AbstractSprite sprite, double elapsedTime) {
		double y = sprite.getPosition().getY();
		double vy = calculateNewVerticalVelocity(sprite, elapsedTime);
		double newy = y + elapsedTime * vy;
		return newy;
	}

	public double calculateNewVerticalVelocity(AbstractSprite sprite, double elapsedTime) {
		//TODO: add velocity to all sprites
		if (sprite instanceof AbstractCharacter) {
			double vy = ((AbstractCharacter) sprite).getVelocity().getYVelocity();
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
