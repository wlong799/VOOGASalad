package game_object.core;

import java.io.Serializable;

/**
 * A class representing 2D velocity.
 * @author Jay
 */
public class Velocity implements Serializable {
	
	private double myXVelocity, myYVelocity;
	
	public Velocity(double xVelocity, double yVelocity) {
		myXVelocity = xVelocity;
		myYVelocity = yVelocity;
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
