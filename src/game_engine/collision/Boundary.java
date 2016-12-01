package game_engine.collision;

import game_object.core.Dimension;
import game_object.core.Position;

/**
 * 
 * @author Michael
 * @author Grant
 *
 *A boundary is a class with position and dimensions. The dimension is a height and a width, and the position is located at the top
 *left of the object. That is, the min y and min x of the object. 
 */
public class Boundary {
    private Position myPosition;
    private Dimension myDimension;
    
    public Boundary (Position position, Dimension dimension) {
        myPosition = position;
        myDimension = dimension;
    }
    /**
     * 
     * @param other the boundary to compare position to this one
     * @return whether or not this boundary overlaps other boundary
     */
    public boolean overlaps (Boundary other) {
        if (this.contains(new Position(other.right(), other.top())) ||
            this.contains(new Position(other.left(), other.top())) ||
            this.contains(new Position(other.right(), other.bottom())) ||
            this.contains(new Position(other.left(), other.bottom()))) {
            return true;
        }
        if (other.contains(new Position(this.right(), this.top())) ||
            other.contains(new Position(this.left(), this.top())) ||
            other.contains(new Position(this.right(), this.bottom())) ||
            other.contains(new Position(this.left(), this.bottom()))) {
            return true;
        }
        return false;
    }
    /**
     * @return the left most x position of the boundary
     */
    public double left () {
        return myPosition.getX();
    }

    /**
     * @return the right most x position of the boundary
     */
    public double right () {
        return myPosition.getX() + myDimension.getWidth();
    }

    /**
     * @return the bottom most y position of the boundary
     */
    public double bottom () {
        return myPosition.getY() + myDimension.getHeight();
    }
    /**
     * @return the top most y position of the boundary
     */
    public double top () {
        return myPosition.getY();
    }
    
    private boolean contains (Position pos) {
        if (pos.getX() <= right() && pos.getX() >= left()) {
            if (pos.getY() <= bottom() && pos.getY() >= top()) {
                return true;
            }
        }
        return false;
    }

}
