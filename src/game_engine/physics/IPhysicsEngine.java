package game_engine.physics;

/**
 * Structure of a physics engine;
 * consists of a CollisionManager and a Location Manager
 * 
 * @author Charlie Wang
 */
public interface IPhysicsEngine {

	public void initCollisionManager();
	
	public void initLocationManager();
	
	public CollisionManager getCollisionManager();
	
	public LocationManager getLocationManager();
	
}
