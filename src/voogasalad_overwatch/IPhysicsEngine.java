package voogasalad_overwatch;

/**
 * @author 
 *sets the physics that should be applied in a level
 */
public interface IPhysicsEngine {

	/**
	 * sets the collisionManager (i.e all of the methods
	 * related to collision detection)
	 * @param collisionManager
	 */
	void setCollisionManager(CollisionManager collisionManager);
	
	/**
	 * @return current collision manager
	 */
	CollisionManager getCollisionManager();
	
//	void setLocationManager(LocationManager locationManager);
//	
//	LocationManager getLocationManager();
//	
//	Point2D getLocationResult(LocationManager locationManager, Point2D currentLocation, int vx, int vy);
//	
//	boolean getCollisionResult(CollisionManager collisionManager, Sprite obj1, Sprite obj2);
}
