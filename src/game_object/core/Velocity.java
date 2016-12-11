package game_object.core;

/**
 * A class representing 2D velocity.
 * @author Jay
 */
public class Velocity {
	
	private double myXVelocity, myYVelocity;
	
	public Velocity(double xVelocity, double yVelocity) {
		myXVelocity = xVelocity;
		myYVelocity = yVelocity;
	}

	public static Velocity getCopiedInstance(Velocity velocity) {
		return new Velocity(velocity.getXVelocity(), velocity.getYVelocity());
	}
	
	public void setXVelocity(double xVelocity) {
		myXVelocity = xVelocity;
	}
	
	public double getXVelocity() {
		return myXVelocity;
	}
	
	public void setYVelocity(double yVelocity) {
		myYVelocity = yVelocity;
	}
	
	public double getYVelocity() {
		return myYVelocity;
	}

}
