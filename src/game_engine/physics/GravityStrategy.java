package game_engine.physics;

public class GravityStrategy extends ConstantStrategy {

    @Override
    public double calculateNewYVelocity (double gravity, double velocity, double elapsedTime) {
        return velocity + (gravity*elapsedTime);
    }
}
