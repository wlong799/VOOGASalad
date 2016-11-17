package game_object.core;

/**
 * A class representing 2D position (x, y), with the convention that
 * (0, 0) is the upper left corner of the screen.
 * 
 * @author Jay, Yilun
 */
public class Position {

    private double myX, myY;

    public Position (double x, double y) {
        myX = x;
        myY = y;
    }

    public Position (Position p) {
        myX = p.getX();
        myY = p.getY();
    }

    public double getX () {
        return myX;
    }

    public void setX (double x) {
        myX = x;
    }

    public double getY () {
        return myY;
    }

    public void setY (double y) {
        myY = y;
    }
}
