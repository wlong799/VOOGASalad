package game_engine.physics;

import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.IPhysicsBody;

/**
 * Engine that calculates all the velocity and position.
 * 
 * @author Charlie Wang
 */
public class PhysicsEngine extends AbstractPhysicsEngine {

	public PhysicsEngine() {
		super();
	}

	@Override
	protected double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		double vx = body.getVelocity().getXVelocity();
		return vx;
	}

	@Override
	protected double calculateNewHorizontalPosition(IPhysicsBody body, double elapsedTime) {
		double x = body.getPosition().getX();
		double vx = calculateNewHorizontalVelocity(body, elapsedTime);
		double newx = x + elapsedTime * vx;
		return newx;
	}
	
	@Override
	protected double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime) {
		double vy = body.getVelocity().getYVelocity();
		double newvy = vy + elapsedTime * myParams.getGravity();
		if (newvy > myParams.getMaxThreshold()) {
			newvy = myParams.getMaxThreshold();
		}
		return newvy;
	}

	@Override
	protected double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime) {
		double y = body.getPosition().getY();
		double vy = calculateNewVerticalVelocity(body, elapsedTime);
		double newy = y + elapsedTime * vy;
		return newy;
	}
	
	public Position calculateNewPosition(IPhysicsBody body, double elapsedTime) {
		return new Position(calculateNewHorizontalPosition(body, elapsedTime),
				calculateNewVerticalPosition(body, elapsedTime));
	}
	
	public Velocity calculateNewVelocity(IPhysicsBody body, double elapsedTime) {
		return new Velocity(calculateNewHorizontalVelocity(body, elapsedTime),
				calculateNewVerticalVelocity(body, elapsedTime));
	}

	@Override
	public void setParameters(String parameter, double value) {
		if (parameter.equals("gravity")) {
			myParams.setGravity(value);
		}
	}
}
