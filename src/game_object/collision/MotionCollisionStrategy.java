package game_object.collision;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.character.ICharacter;
import game_object.simulation.ICollisionBody;


public class MotionCollisionStrategy<A extends ICollisionBody, B extends ICollisionBody>
        extends AbstractCollisionStrategy<A, B> {

    private boolean myVerticalBounce = false;
    private boolean myHorizontalBounce = false;

    public void setHorizontalBounce (boolean horizontalBounce) {
        myHorizontalBounce = horizontalBounce;
    }

    public void setVerticalBounce (boolean verticalBounce) {
        myVerticalBounce = verticalBounce;
    }

 
    public boolean getHorizontalBounce () {
        return myHorizontalBounce;
    }

    public boolean getVerticalBounce () {
        return myVerticalBounce;
    }

    @Override
    public void applyCollision (A a, B b, CollisionDirection collisionDirection) {
        switch (collisionDirection) {
            case TOP:
                applyTopCollision(a, b);
                break;
            case BOTTOM:
                applyBottomCollision(a, b);
                break;
            case LEFT:
                applyLeftCollision(a, b);
                break;
            case RIGHT:
                applyRightCollision(a, b);
                break;
            default:
                break;
        }
    }

    private void applyTopCollision (A a, B b) {
        if (myVerticalBounce) {
            a.getVelocity().setYVelocity(-1.0 * a.getVelocity().getYVelocity());
        } else {
            a.getPosition().setY(b.getPosition().getY() + b.getDimension().getHeight());
            a.getVelocity().setYVelocity(0);
        }
        
    }

    private void applyBottomCollision (A a, B b) {
        if (a instanceof ICharacter) {
            ((ICharacter) a).resetCurrentJumps();
        }
        if (myVerticalBounce) {
            a.getVelocity().setYVelocity(-1.0 * a.getVelocity().getYVelocity());
        } else {
        	a.getVelocity().setYVelocity(0);
            a.getPosition().setY(b.getPosition().getY() - a.getDimension().getHeight());
        }
    }

    private void applyLeftCollision (A a, B b) {
        if (myHorizontalBounce) {
            a.getVelocity().setXVelocity(-1.0 * a.getVelocity().getXVelocity());
        } else {
            a.getPosition().setX(b.getPosition().getX() + b.getDimension().getWidth());
            a.getVelocity().setXVelocity(0);
        }
    }

    private void applyRightCollision (A a, B b) {
        if (myHorizontalBounce) {
            a.getVelocity().setXVelocity(-1.0 * a.getVelocity().getXVelocity());
        } else {
            a.getPosition().setX(b.getPosition().getX() - a.getDimension().getWidth());
            a.getVelocity().setXVelocity(0);
        }
    }

}
