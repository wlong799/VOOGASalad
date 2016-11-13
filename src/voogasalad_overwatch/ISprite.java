package voogasalad_overwatch;

public interface ISprite {
    
    // set this sprites position
    void setPosition(Position pos);
    // get this sprite's position
    Position getPosition();
    // kill this sprite
    void kill();
    // check if it is dead
    boolean isDead();
    // what to do when this sprite collides with another sprite
    void collided(ISprite otherSprite);
    
}
