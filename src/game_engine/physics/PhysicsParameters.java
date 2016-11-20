package game_engine.physics;

/**
 * Class that represent the basic behaviors of gravity
 * 
 * @author Charlie Wang
 * @author Grant Costa
 */
public class PhysicsParameters {
	private static final double G = 10;
	private static final double AF = 1;
	private static final double GF = 0.9;
	private static final double T = .1;
	private double myGravity;
	private double myAirFriction;
	private double myGroundFriction;
	private double myThreshold;

	public PhysicsParameters() {
		setGravity(G);
		setAirFriction(AF);
		setGroundFriction(GF);
		setThreshold(T);
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
	
	public void setThreshold(double t) {
		myThreshold = t;
	}
	
	public double getThreshold() {
		return myThreshold;
	}
}
