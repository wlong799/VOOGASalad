package game_engine.physics;

import game_object.simulation.IPhysicsBody;

public class PhysicsEngineWithFriction extends PhysicsEngine{
	
	protected double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		return 0;
	}

	@Override
	protected double calculateNewHorizontalPosition(IPhysicsBody body, double elapsedTime) {
		return 0;
	}
	
	public void setParameters(String parameter, double value) {
		if (parameter.equals("gravity")) {
			myParams.setGravity(value);
		} else if (parameter.equals("airfriction")) {
			myParams.setAirFriction(value);
		} else if (parameter.equals("groundfriction")) {
			myParams.setGroundFriction(value);
		} else {
			//
		}
	}
}
