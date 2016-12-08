package game_engine.physics;

public interface IPhysicsStrategy {

    public double calculateNewYVelocity(double gravity, double velocity, double elapsedTime);
    public double calculateNewXVelocity(double friction, double velocity, double elapsedTime);

    
}
