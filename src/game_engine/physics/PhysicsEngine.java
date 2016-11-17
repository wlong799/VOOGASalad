package game_engine.physics;

import game_object.character.AbstractCharacter;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;

/**
 * Engine that handles all the physical movements and collisions
 * 
 * @author Charlie Wang
 */
public class PhysicsEngine extends AbstractPhysicsEngine {

	public PhysicsEngine() {
		super();
	}

	@Override
	public double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		double vx = body.getVelocity().getXVelocity();
		double newvx = vx + elapsedTime * myGravity.getGravity();
		return newvx;
	}

	@Override
	public double calculateNewHorizontalPosition(IPhysicsBody body, double elapsedTime) {
		double x = body.getPosition().getX();
		double vx = calculateNewHorizontalVelocity(body, elapsedTime);
		double newx = x + elapsedTime * vx;
		return newx;
	}
	
	@Override
	public double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime) {
		double vy = body.getVelocity().getYVelocity();
		double newvy = vy + elapsedTime * myGravity.getGravity();
		return newvy;
	}

	@Override
	public double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime) {
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
}
