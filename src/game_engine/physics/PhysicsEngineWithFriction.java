package game_engine.physics;

import game_object.simulation.IPhysicsBody;

/**
 * Added air friction and ground friction to the model
 * 
 * @author Charlie Wang
 */
public class PhysicsEngineWithFriction extends PhysicsEngine {

	protected double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		double vx = body.getVelocity().getXVelocity();
		double friction = (body.getVelocity().getYVelocity() == 0) ? myParams.getGroundFriction()
				: myParams.getAirFriction();
		double newvx = vx * (1 - friction);
		if (newvx < myParams.getMinThreshold()) {
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
