package game_object.collision;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.*;
import game_object.simulation.ICollisionBody;
public class BrickWallCollisionStrategy<A extends ICollisionBody, B extends ICollisionBody> extends AbstractCollisionStrategy<A, B> {

    @Override
    public void applyCollision (A a, B b, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
    }

}
