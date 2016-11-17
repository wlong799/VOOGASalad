package game_engine.physics;

import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.AbstractCharacter;
import game_object.core.AbstractSprite;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;

/**
 * Engine that handles all the physical movements and collisions
 * 
 * @author Charlie Wang
 */
public class PhysicsEngine extends AbstractPhysicsEngine{
	
	public PhysicsEngine() {
		super();
	}
	
	public void updateSprites(Level myCurrentLevel) {
		
	}
	
	public void setGroundBlocks(List<AbstractBlock> blocks) {
		myLocationManager.setGroundBlocks(blocks);
	}
	
	private void updateVerticalPositionAndVelocity(double newy, double newvy, boolean touched, IPhysicsBody sprite) {
		if (touched) {
			sprite.getVelocity().setYVelocity(0);
			return;
		}
		
		//new position = current position + dy
		sprite.getPosition().setY(newy);
		
		//new velocity = current velocity +dvy
		sprite.getVelocity().setYVelocity(newvy);
	}
	
}
