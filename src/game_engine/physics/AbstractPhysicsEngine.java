package game_engine.physics;

public abstract class AbstractPhysicsEngine implements IPhysicsEngine {
	protected CollisionManager myCollisionManager;
	protected LocationManager myLocationManager;

	public AbstractPhysicsEngine() {
		initCollisionManager();
		initLocationManager();
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

}
