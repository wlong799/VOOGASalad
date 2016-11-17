package game_engine.physics;

import java.util.ArrayList;
import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.AbstractCharacter;
import game_object.level.Level;

/**
 * Engine that handles all the physical movements and collisions
 * 
 * @author Charlie Wang
 */
public class PhysicsEngine extends AbstractPhysicsEngine{
	
	public PhysicsEngine(int fps) {
		super(fps);
	}
	
	public void updateBlocks(Level myCurrentLevel) {
		
	}
	
	public void setGroundBlocks(List<AbstractBlock> blocks) {
		myLocationManager.setGroundBlocks(blocks);
	}
	
	private void updateVerticalPositionAndVelocity(double newy, double newvy, boolean touched, AbstractCharacter character) {
		if (touched) {
			character.getVelocity().setYVelocity(0);
			return;
		}
		
		//new position = current position + dy
		character.getPosition().setY(newy);
		
		//new velocity = current velocity +dvy
		character.getVelocity().setYVelocity(newvy);
	}
	
}
