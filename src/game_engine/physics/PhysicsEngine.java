package game_engine.physics;

public class PhysicsEngine implements IPhysicsEngine{
	private CollisionManager myCollisionManager;
	private LocationManager myLocationManager;

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
	
	
}
