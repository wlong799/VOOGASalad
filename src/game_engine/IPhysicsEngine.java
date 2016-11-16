package game_engine;

public interface IPhysicsEngine {

	public void initCollisionManager();
	
	public void initLocationManager();
	
	public CollisionManager getCollisionManager();
	
	public LocationManager getLocationManager();
	
}
