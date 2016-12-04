package game_engine.physics;

import game_object.level.Level;
import game_object.simulation.IPhysicsBody;
import game_object.weapon.Projectile;

/**
 * Added air friction and ground friction to the model
 * 
 * @author Charlie Wang
 */
public class PhysicsEngineWithFriction extends PhysicsEngine {

	public PhysicsEngineWithFriction(Level level) {
		super(level);
	}

	public double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		double vx = body.getVelocity().getXVelocity();
		double newvx = vx;
		if (!existLeftRight) {
			double friction = (body.getVelocity().getYVelocity() == 0) ? myLevel.getPhysicsParameters().getGroundFriction()
					: myLevel.getPhysicsParameters().getAirFriction();
			newvx = vx * (1 - friction);
		}
		if (Math.abs(newvx) < myLevel.getPhysicsParameters().getMinThreshold()) {
			newvx = 0;
		}
		//System.out.println(body.getVelocity().getXVelocity());
		return newvx;
	}
	
	public void setParameters(PhysicsParameterSetOptions option, double value) {
		if (option == PhysicsParameterSetOptions.GRAVITY) {
			myLevel.getPhysicsParameters().setGravity(value);
		} else if (option == PhysicsParameterSetOptions.AIRFRICTION) {
			myLevel.getPhysicsParameters().setAirFriction(value);
		} else if (option == PhysicsParameterSetOptions.GROUNDFRICTION) {
			myLevel.getPhysicsParameters().setGroundFriction(value);
		} else if (option == PhysicsParameterSetOptions.MINTHRESHOLD) {
			myLevel.getPhysicsParameters().setMinThreshold(value);
		} else if (option == PhysicsParameterSetOptions.MAXTHRESHOLD) {
			myLevel.getPhysicsParameters().setMaxThreshold(value);
		} else {
			return;
		}
	}
}
