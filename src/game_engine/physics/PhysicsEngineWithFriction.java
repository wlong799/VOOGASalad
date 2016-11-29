package game_engine.physics;

import game_object.level.Level;
import game_object.simulation.IPhysicsBody;

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
			double friction = (body.getVelocity().getYVelocity() == 0) ? myParams.getGroundFriction()
					: myParams.getAirFriction();
			newvx = vx * (1 - friction);
		}
		if (Math.abs(newvx) < myParams.getMinThreshold()) {
			newvx = 0;
		}
		return newvx;
	}

	public void setParameters(PhysicsParameterSetOptions option, double value) {
		if (option == PhysicsParameterSetOptions.GRAVITY) {
			myParams.setGravity(value);
		} else if (option == PhysicsParameterSetOptions.AIRFRICTION) {
			myParams.setAirFriction(value);
		} else if (option == PhysicsParameterSetOptions.GROUNDFRICTION) {
			myParams.setGroundFriction(value);
		} else if (option == PhysicsParameterSetOptions.MINTHRESHOLD) {
			myParams.setMinThreshold(value);
		} else if (option == PhysicsParameterSetOptions.MAXTHRESHOLD) {
			myParams.setMaxThreshold(value);
		} else {
			//
		}
	}
}
