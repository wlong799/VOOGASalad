package game_engine.physics;

import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;

/**
 * Abstract class for a basic physics engine implements all the updater
 * 
 * @author Charlie Wang
 */
public abstract class AbstractPhysicsEngine implements IPhysicsEngine {
	protected PhysicsParameters myParams;
	protected boolean existLeftRight;

	protected AbstractPhysicsEngine(Level level) {
		myParams = level.getPhysicsParameters();
	}

	public void updateHorizontalPositionAndVelocity(double newx, double newvx, IPhysicsBody body) {
		body.getPosition().setX(newx);
		body.getVelocity().setXVelocity(newvx);
	}

	public void updateVerticalPositionAndVelocity(double newy, double newvy, IPhysicsBody body) {
		body.getPosition().setY(newy);
		body.getVelocity().setYVelocity(newvy);
	}

	public void updatePosition(Position position, IPhysicsBody body) {
		body.setPosition(position);
	}

	public void updateVelocity(Velocity velocity, IPhysicsBody body) {
		body.setVelocity(velocity);
	}

	public void updatePositionAndVelocity(double newx, double newvx, double newy, double newvy, IPhysicsBody body) {

		updateHorizontalPositionAndVelocity(newx, newvx, body);
		updateVerticalPositionAndVelocity(newy, newvy, body);

	}

	public void updatePositionAndVelocity(Position position, Velocity velocity, IPhysicsBody body) {
		updatePosition(position, body);
		updateVelocity(velocity, body);

	}

	public void setExisted(boolean exist) {
		existLeftRight = exist;
	}
}
