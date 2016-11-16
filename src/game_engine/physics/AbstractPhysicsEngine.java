package game_engine.physics;

public abstract class AbstractPhysicsEngine implements IPhysicsEngine {

	public AbstractPhysicsEngine() {
		initCollisionManager();
		initLocationManager();
	}

	public abstract void initCollisionManager();

	public abstract void initLocationManager();

	public abstract CollisionManager getCollisionManager();

	public abstract LocationManager getLocationManager();

}
