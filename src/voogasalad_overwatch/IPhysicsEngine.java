package voogasalad_overwatch;

import javafx.geometry.Point2D;

public interface IPhysicsEngine {

	void setCollisionManager(CollisionManager collisionManager);
	
	CollisionManager getCollisionManager();
	
	void setLocationManager(LocationManager locationManager);
	
	LocationManager getLocationManager();
	
	Point2D getLocationResult(LocationManager locationManager, Point2D currentLocation, int vx, int vy);
	
	boolean getCollisionResult(CollisionManager collisionManager, Sprite obj1, Sprite obj2);
}
