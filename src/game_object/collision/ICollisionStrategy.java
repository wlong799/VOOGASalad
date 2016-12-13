package game_object.collision;

import java.io.Serializable;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.simulation.ICollisionBody;

/**
 * 
 * @author Jay
 *
 * @param <A> First ICollisionBody
 * @param <B> Second ICollisionBody
 */
public interface ICollisionStrategy<A extends ICollisionBody, B extends ICollisionBody> extends Serializable {
	
	void setValid(boolean valid);
	
	boolean isValid();
	
	/**
	 * Apply the predefined behavior when this collision happened.
	 * @param a
	 * @param b
	 * @param collisionDirection this collision happens on the collisionDirection of A.
	 * e.g. if it's TOP, it means this collision happens on top of A.
	 */
	void applyCollision(A a, B b, CollisionDirection collisionDirection);
	
	
	
}
