package game_engine.physics;

import game_object.simulation.IPhysicsBody;

/**
 * Structure of a physics engine; calculates new position and velocity
 * 
 * @author Charlie Wang
 */
public interface IPhysicsEngine {

	public double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime);
	
	public double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime);
	
	public double calculateNewHorizontalPosition(IPhysicsBody body, double elapsedTime);
	
	public double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime);
	
	public void updateHorizontalPositionAndVelocity(double newx, double newvx, IPhysicsBody body);
	
	public void updateVerticalPositionAndVelocity(double newy, double newvy, IPhysicsBody body);
}
