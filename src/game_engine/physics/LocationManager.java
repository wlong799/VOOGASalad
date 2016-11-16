package game_engine.physics;

import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.AbstractCharacter;

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
	
	public double calculateNewVerticalPosition(AbstractCharacter character, double elapsedTime) {
		double y = character.getPosition().getY();
		double vy = calculateNewVerticalVelocity(character, elapsedTime);
		double newy = y + elapsedTime * vy;
		return newy;
	}

	public double calculateNewVerticalVelocity(AbstractCharacter character, double elapsedTime) {
		double vy = character.getVelocity().getYVelocity();
		double newvy = vy + elapsedTime * myGravity.getGravity();
		return newvy;
	}
	
	public boolean checkTouchedGround(AbstractCharacter character, double newy) {
		//TODO: loop through all the (can-stand-on) blocks can check if they collide with the top border of the hit box
		return false;
	}

}
