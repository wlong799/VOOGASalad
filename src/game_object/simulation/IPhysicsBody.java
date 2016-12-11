package game_object.simulation;

import game_engine.physics.IPhysicsStrategy;
import game_object.core.Velocity;

/**
 * A physics body that, if getAffectedByPhysics() returns true, will be affected by physics
 * @author Jay
 */
public interface IPhysicsBody extends IBodyWithPosition {
	
	void setAffectedByPhysics(boolean affectedByPhysics);
	
	boolean getAffectedByPhysics();

	void setVelocity(Velocity velocity); 
	
	Velocity getVelocity();
	
	void setPreviousVelocity(Velocity velocity);
	
	Velocity getPreviousVelocity();
	
	void setPhysics(IPhysicsStrategy physics);
	
	IPhysicsStrategy getPhysics();

}
