package game_engine.physics;

import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.AbstractCharacter;
import game_object.core.AbstractSprite;
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

	public void updateHorizontalPositionAndVelocity(double newx, double newvx, IPhysicsBody body) {
		// new position = current position + dx
		body.getPosition().setY(newx);

		// new velocity = current velocity +dvx
		body.getVelocity().setYVelocity(newvx);
	}

	public void updateVerticalPositionAndVelocity(double newy, double newvy, IPhysicsBody sprite) {
		// new position = current position + dy
		sprite.getPosition().setY(newy);

		// new velocity = current velocity +dvy
		sprite.getVelocity().setYVelocity(newvy);
	}

}
