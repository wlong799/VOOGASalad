package game_engine.physics;

import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;
import game_object.weapon.Projectile;
import game_object.weapon.Weapon;

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
	public double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		double vx = body.getVelocity().getXVelocity();
		if (calculateNewVerticalVelocity(body, elapsedTime) == 0 && !existLeftRight) {
			vx = 0;
		}
		return vx;
	}

	private double calculateNewHorizontalVelocityHelper(IPhysicsBody body, double elapsedTime) {
		return 0;
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
		double newvy;
		if (body instanceof Projectile) {
			Projectile projectile = (Projectile) body;
			System.out.println(projectile.getPosition().getY());
			if (!projectile.getModel().isAffectedByGravity()) {
				newvy = projectile.getModel().getInitalVelocity().getYVelocity();
			} else {
				newvy = calculateNewVerticalVelocityHelper(body, elapsedTime);
			}
		} else {
			newvy = calculateNewVerticalVelocityHelper(body, elapsedTime);
		}
		return newvy;
	}

	private double calculateNewVerticalVelocityHelper(IPhysicsBody body, double elapsedTime) {
		double vy = body.getVelocity().getYVelocity();
		double newvy = vy + elapsedTime * myLevel.getPhysicsParameters().getGravity();
		if (Math.abs(newvy) > myLevel.getPhysicsParameters().getMaxThreshold()) {
			newvy = newvy > 0 ? myLevel.getPhysicsParameters().getMaxThreshold()
					: -myLevel.getPhysicsParameters().getMaxThreshold();
		}
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

	@Override
	public void setParameters(PhysicsParameterSetOptions option, double value) {
		if (option == PhysicsParameterSetOptions.GRAVITY) {
			myLevel.getPhysicsParameters().setGravity(value);
		}
	}
}
