package game_engine.physics;

/**
 * Class that represent the basic behaviors of gravity and friction
 * 
 * @author Charlie Wang
 * @author Grant Costa
 */
public class PhysicsParameters {
	private static final double G = 50;
	private static final double AF = 0;
	private static final double GF = 0.1;
	private static final double TMIN = 1;
	private static final double TMAX = 1000;
	private double myGravity;
	private double myAirFriction;
	private double myGroundFriction;
	private double myMinThreshold;
	private double myMaxThreshold;

	public PhysicsParameters() {
		setGravity(G);
		setAirFriction(AF);
		setGroundFriction(GF);
		setMinThreshold(TMIN);
		setMaxThreshold(TMAX);
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
	
	public void setMinThreshold(double t) {
		myMinThreshold = t;
	}
	
	public double getMinThreshold() {
		return myMinThreshold;
	}
	
	public void setMaxThreshold(double t) {
		myMaxThreshold = t;
	}
	
	public double getMaxThreshold() {
		return myMaxThreshold;
	}
}
