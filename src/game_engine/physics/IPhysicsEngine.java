package game_engine.physics;

/**
 * Structure of a physics engine;
 * consists of a CollisionManager and a Location Manager
 * 
 * @author Charlie Wang
 */
public interface IPhysicsEngine {

	/**
	 * Initializes the collision manager
	 */
	public void initCollisionManager();
	
	/**
	 * Initializes the location manager
	 */
	public void initLocationManager();
	
	public CollisionManager getCollisionManager();
	
	public LocationManager getLocationManager();
	
}
