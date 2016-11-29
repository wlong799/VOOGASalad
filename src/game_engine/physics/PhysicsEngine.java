package game_engine.physics;

import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;

/**
 * Engine that calculates all the velocity and position.
 * 
 * @author Charlie Wang
 */
public class PhysicsEngine extends AbstractPhysicsEngine {

	public PhysicsEngine(Level level) {
		super(level);
	}

	@Override
	protected double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		double vx = body.getVelocity().getXVelocity();
		if (calculateNewVerticalVelocity(body, elapsedTime) == 0 && !existLeftRight) {
			vx = 0;
		}
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
		if (Math.abs(newvy) > myParams.getMaxThreshold()) {
			newvy = newvy > 0 ? myParams.getMaxThreshold() : -myParams.getMaxThreshold();
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
	public void setParameters(PhysicsParameterSetOptions option, double value) {
		if (option == PhysicsParameterSetOptions.GRAVITY) {
			myParams.setGravity(value);
		}
	}
}
