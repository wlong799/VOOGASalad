package game_engine.physics;

public class ConstantStrategy implements IPhysicsStrategy {

    @Override
    public double calculateNewYVelocity (double gravity, double velocity, double elapsedTime) {
        return velocity;
    }

    @Override
    public double calculateNewXVelocity (double friction, double velocity, double elapsedTime) {
        return velocity;
    }

}
