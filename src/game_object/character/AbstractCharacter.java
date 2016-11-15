package game_object.character;

import game_object.AbstractSprite;
import game_object.CollisionBody;
import game_object.PhysicsBody;
import game_object.Velocity;

/**
 * A base class for all characters.
 * @author Jay
 */
public abstract class AbstractCharacter extends AbstractSprite implements CollisionBody, PhysicsBody {
	
	protected double myMaxHP;
	protected double myCurrentHP;
	protected boolean myAffectedByPhysics;
	protected Velocity myVelocity;
	
	public void setMaxHP(int maxHP) {
		myMaxHP = maxHP;
	}
	
	public double getMaxHP() {
		return myMaxHP;
	}
	
	public void setCurrentHP(double currentHP) {
		myCurrentHP = currentHP;
	}
	
	public double getCurrentHP() {
		return myCurrentHP;
	}
	
	/* PhysicsBody */
	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		myAffectedByPhysics = affectedByPhysics;
	}
	
	@Override
	public boolean getAffectedByPhysics() {
		return myAffectedByPhysics;
	}
	
	@Override
	public void setVelocity(Velocity velocity) {
		myVelocity = velocity;
	}
	
	@Override
	public Velocity getVelocity() {
		return myVelocity;
	}
}
