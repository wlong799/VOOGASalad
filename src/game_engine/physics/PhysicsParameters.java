package game_engine.physics;

/**
 * Class that represent the basic behaviors of gravity
 * 
 * @author Charlie Wang
 */
public class PhysicsParameters {
	private static final double G = 10;
	private static final double AF = -2;
	private static final double GF = -3;
	private double myGravity;
	private double myAirFriction;
	private double myGroundFriction;

	public PhysicsParameters() {
		setGravity(G);
		setAirFriction(AF);
		setGroundFriction(GF);
	}

	public void setGravity(double g) {
		myGravity = g;
	}
	
	public double getGravity() {
		return myGravity;
	}

	public void setAirFriction(double f) {
		myAirFriction = f;
	}
	
	public double getAirFriction() {
		return myAirFriction;
	}
	
	public void setGroundFriction(double f) {
		myGroundFriction = f;
	}
	
	public double getGroundFriction() {
		return myGroundFriction;
	}
}
