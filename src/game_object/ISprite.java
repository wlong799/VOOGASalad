package game_object;

import javafx.scene.image.ImageView;
import voogasalad_overwatch.Collision;

import java.util.*;

public interface ISprite {
    
    // set this sprites position
    void setPosition(Position pos);
    // get this sprite's position
    Position getPosition();
    // set this sprite's isDead parameter
    void setDead(boolean dead);
    // check if it is dead
    boolean isDead();
    // what to do when this sprite collides with another sprite
    void collided(ISprite otherSprite);
    //sets the new hp of sprite
    void setHP(double hp);
    double getHP();
    //sets the new collision property of sprite
    void setCollisionProperty(Collision coll);
    
    ArrayList<String> getImagePaths();
    void setImagePaths(ArrayList<String> imgPaths);
    
}
