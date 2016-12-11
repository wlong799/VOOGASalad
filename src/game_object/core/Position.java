package game_object.core;

import java.io.Serializable;

/**
 * A class representing 2D position (x, y), with the convention that
 * (0, 0) is the upper left corner of the screen.
 * 
 * @author Jay, Yilun
 */
public class Position implements Serializable {
	
	private static final long serialVersionUID = -7601906453626239644L;
	private double myX;
	private double myY;
	private double myZ; // deals with front-to-back ordering of layers onscreen. Larger means more front.
	
	public Position(double x, double y) {
		myX = x;
		myY = y;
	}
	
	public Position(double x, double y, double z) {
		this(x, y);
		myZ = z;
	}
	
	public static Position getCopiedInstance(Position position) {
		return new Position(position.getX(), position.getY(), position.getZ());
	}

	public double getX() {
		return myX;
	}
	
	public void setX(double x) {
		myX = x;
	}
	
	public double getY() {
		return myY;
	}
	
	public void setY(double y) {
		myY = y;
	}
	
	public double getZ() {
		return myZ;
	}
	
	public void setZ(double z) {
		myZ = z;
	}
	
	@Override
	public String toString() {
	    return myX + ", " + myY;
	}

}
