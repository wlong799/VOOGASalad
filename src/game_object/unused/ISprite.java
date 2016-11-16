package game_object.unused;

import java.util.ArrayList;

import game_object.core.Position;

public interface ISprite {
    
    // set this sprites position
    void setPosition(Position pos);
    
    // get this sprite's position
    Position getPosition();
    
    // set this sprite's isDead parameter
    // void setDead(boolean dead);
    
    // check if it is dead
    // boolean isDead();
    
    // what to do when this sprite collides with another sprite
    // void collided(CollisionBody otherBody);
    
    //sets the new collision property of sprite
    // void setCollisionProperty(Collision coll);
    
    ArrayList<String> getImagePaths();
    void setImagePaths(ArrayList<String> imgPaths);
    
}
