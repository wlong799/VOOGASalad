package game_object.character;

import java.util.ArrayList;

import game_object.core.AbstractSprite;
import game_object.core.Velocity;
import game_object.weapon.Weapon;

/**
 * A base class for all characters.
 * @author Jay
 */
abstract class AbstractCharacter extends AbstractSprite implements ICharacter {

	protected double myMaxHP;
	protected double myCurrentHP;
	protected boolean myAffectedByPhysics;
	protected Velocity myVelocity;
	protected Weapon myCurrentWeapon;
	protected boolean myDead;
	
	protected AbstractCharacter(double x, double y, ArrayList<String> imgPaths, double maxHP) {
		super(x, y, imgPaths);
		myMaxHP = maxHP;
		myCurrentHP = myMaxHP;
		myVelocity = new Velocity(0, 0);
		myDead = false;
	}
	
	@Override
	public Weapon getCurrentWeapon() {
		return myCurrentWeapon;
	}

	@Override
	public void setCurrentWeapon(Weapon currentWeapon) {
		myCurrentWeapon = currentWeapon;
	}
	
	/* IMortal Implementations */
	@Override
	public void setMaxHP(int maxHP) {
		myMaxHP = maxHP;
	}
	
	@Override
	public double getMaxHP() {
		return myMaxHP;
	}
	
	@Override
	public void setCurrentHP(double currentHP) {
		myCurrentHP = currentHP;
	}
	
	@Override
	public double getCurrentHP() {
		return myCurrentHP;
	}
	
	@Override
	public void setDead(boolean dead) {
		myDead = dead;
	}

	@Override
	public boolean getDead() {
		return myDead;
	}

	/* IPhysicsBody Implementations */
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
