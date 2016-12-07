package game_engine.physics;

public class GravityFrictionStrategy extends GravityStrategy {

    @Override
    public double calculateNewXVelocity (double friction, double velocity, double elapsedTime) {
        return velocity*(1-friction);
    }

}
