package game_engine.physics;

import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.AbstractCharacter;
import game_object.core.AbstractSprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.IPhysicsBody;

/**
 * Abstract class for a basic physics engine
 * 
 * @author Charlie Wang
 */
public abstract class AbstractPhysicsEngine implements IPhysicsEngine {
	Gravity myGravity;

	public AbstractPhysicsEngine() {
		myGravity = new Gravity();
	}

	public abstract double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime);

	public abstract double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime);

	public abstract double calculateNewHorizontalPosition(IPhysicsBody sprite, double elapsedTime);

	public abstract double calculateNewHorizontalVelocity(IPhysicsBody sprite, double elapsedTime);

	public abstract Position calculateNewPosition(IPhysicsBody body, double elapsedTime);
	
	public abstract Velocity calculateNewVelocity(IPhysicsBody body, double elapsedTime);
	
	public void updateHorizontalPositionAndVelocity(double newx, double newvx, IPhysicsBody body) {
		body.getPosition().setY(newx);
		body.getVelocity().setYVelocity(newvx);
	}

	public void updateVerticalPositionAndVelocity(double newy, double newvy, IPhysicsBody body) {
		body.getPosition().setY(newy);
		body.getVelocity().setYVelocity(newvy);
	}
	
	public void updatePositionAndVelocity(double newx, double newvx, double newy, double newvy, IPhysicsBody body) {
		updateHorizontalPositionAndVelocity(newx, newvx, body);
		updateVerticalPositionAndVelocity(newy, newvy, body); 
	}
}
