package game_engine.physics;

import game_object.character.AbstractCharacter;
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
	public double calculateNewHorizontalPosition(IPhysicsBody sprite, double elapsedTime) {
		return 0;
	}

	@Override
	public double calculateNewHorizontalVelocity(IPhysicsBody sprite, double elapsedTime) {
		return 0;
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
}
