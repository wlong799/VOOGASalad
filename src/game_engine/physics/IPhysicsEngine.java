package game_engine.physics;

import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.IPhysicsBody;

/**
 * Structure of a physics engine; calculates new position and velocity
 * 
 * @author Charlie Wang
 */
public interface IPhysicsEngine {

	public Position calculateNewPosition(IPhysicsBody body, double elapsedTime);
	
	public Velocity calculateNewVelocity(IPhysicsBody body, double elapsedTime);
	
	public void updateHorizontalPositionAndVelocity(double newx, double newvx, IPhysicsBody body);
	
	public void updateVerticalPositionAndVelocity(double newy, double newvy, IPhysicsBody body);
	
	public void updatePosition(Position position, IPhysicsBody body);
	
	public void updateVelocity(Velocity velocity, IPhysicsBody body);
	
	public void updatePositionAndVelocity(double newx, double newvx, double newy, double newvy, IPhysicsBody body);
	
	public void updatePositionAndVelocity(Position position, Velocity velocity, IPhysicsBody body);
}
