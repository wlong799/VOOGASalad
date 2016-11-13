package voogasalad_overwatch;

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
    
}
