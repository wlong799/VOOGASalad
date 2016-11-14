package game_object.character;

import game_object.AbstractSprite;
import game_object.CollisionBody;

/**
 * A base class for all characters.
 * @author Jay
 */
public abstract class AbstractCharacter extends AbstractSprite implements CollisionBody {
	
	protected double myMaxHP;
	protected double myCurrentHP;
	protected boolean myAffectedByPhysics;
	
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
	
}
