package game_engine.collision;

import game_object.core.Dimension;
import game_object.core.Position;

/**
 * 
 * @author Michael
 * @author Grant
 *
 */
public class Boundary {
    private Position myPosition;
    private Dimension myDimension;

    public Boundary (Position position, Dimension dimension) {
        myPosition = position;
        myDimension = dimension;
    }

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

    public double left () {
        return myPosition.getX();
    }

    public double right () {
        return myPosition.getX() + myDimension.getWidth();
    }

    public double bottom () {
        return myPosition.getY() + myDimension.getHeight();
    }

    public double top () {
        return myPosition.getY();
    }

    private boolean contains (Position pos) {
        if (pos.getX() < right() && pos.getX() > left()) {
            if (pos.getY() < bottom() && pos.getY() > top()) {
                return true;
            }
        }
        return false;
    }

}
