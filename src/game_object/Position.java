package game_object;

/**
 * A class representing 2D position (x, y), with the convention that
 * (0, 0) is the upper left corner of the screen. 
 * @author Jay, Yilun
 */
public class Position {
	
	private double myX, myY;
	
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
}
