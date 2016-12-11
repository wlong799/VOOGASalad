package game_engine.physics;

import java.io.Serializable;

public interface IPhysicsStrategy extends Serializable {

    public double calculateNewYVelocity(double gravity, double velocity, double elapsedTime);
    public double calculateNewXVelocity(double friction, double velocity, double elapsedTime);

}
