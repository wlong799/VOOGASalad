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
	private CollisionManager myCollisionManager;
	private LocationManager myLocationManager;

	public PhysicsEngine() {
		super();
	}
	
	@Override
	public void initCollisionManager() {
		myCollisionManager = new CollisionManager();
	}

	@Override
	public void initLocationManager() {
		myLocationManager = new LocationManager();
	}

	@Override
	public CollisionManager getCollisionManager() {
		return myCollisionManager;
	}

	@Override
	public LocationManager getLocationManager() {
		return myLocationManager;
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
