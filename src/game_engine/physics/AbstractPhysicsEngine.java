package game_engine.physics;

/**
 * Abstract class for a basic physics engine
 * 
 * @author Charlie Wang
 */
public abstract class AbstractPhysicsEngine implements IPhysicsEngine {
	protected CollisionManager myCollisionManager;
	protected LocationManager myLocationManager;
	protected double myFrameTime;

	public AbstractPhysicsEngine(int fps) {
		initCollisionManager();
		initLocationManager();
		myFrameTime = 1.0 / fps;
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
