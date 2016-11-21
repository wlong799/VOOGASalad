package game_engine.physics;

import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.IPhysicsBody;

/**
 * Abstract class for a basic physics engine implements all the updater
 * 
 * @author Charlie Wang
 */
public abstract class AbstractPhysicsEngine implements IPhysicsEngine {
	protected PhysicsParameters myParams;

	protected AbstractPhysicsEngine() {
		myParams = new PhysicsParameters();
	}

	protected abstract double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime);

	protected abstract double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime);

	protected abstract double calculateNewHorizontalPosition(IPhysicsBody sprite, double elapsedTime, boolean exist);

	protected abstract double calculateNewHorizontalVelocity(IPhysicsBody sprite, double elapsedTime, boolean exist);

	public abstract Position calculateNewPosition(IPhysicsBody body, double elapsedTime, boolean exist);

	public abstract Velocity calculateNewVelocity(IPhysicsBody body, double elapsedTime, boolean exist);

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
		if (body.getAffectedByPhysics()) {
			updatePosition(position, body);
			updateVelocity(velocity, body);
		}
	}
}
