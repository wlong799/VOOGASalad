package game_object.block;

import java.util.ArrayList;

import game_object.Dimension;
import game_object.Position;

public interface IBlock {

	// set this sprites position
    void setPosition(Position pos);
    // get this sprite's position
    Position getPosition();
    // set this sprite's isDead parameter
    
    void setDimension(Dimension d);
    Dimension getDimension();
    
    ArrayList<String> getImagePaths();
    void setImagePaths(ArrayList<String> imgPaths);
	
}
