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
	
	Velocity getVelocity();

	void setVelocity(Velocity velocity); 
	
	IPhysicsStrategy getPhysics();
	
	void setPhysics(IPhysicsStrategy physics);

}
