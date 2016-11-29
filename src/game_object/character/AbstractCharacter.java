package game_object.character;

import java.util.List;

import game_object.core.AbstractSprite;
import game_object.core.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.Position;
import game_object.weapon.Weapon;

/**
 * A base class for all characters.
 * @author Jay
 */
abstract class AbstractCharacter extends AbstractSprite implements ICharacter {

	protected double myMaxHP = DefaultConstants.CHARACTER_MAX_HP;
	protected double myCurrentHP = DefaultConstants.CHARACTER_MAX_HP;
	protected boolean myDead = false;
        protected int myCurrentJumps;
	protected Weapon myCurrentWeapon;
	
	protected AbstractCharacter(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
	}
	
	/* IMortal Implementations */
	@Override
	public void setMaxHP(int maxHP, boolean setCurrentHPtoMax) {
		myMaxHP = maxHP;
		if (setCurrentHPtoMax) {
			myCurrentHP = maxHP;
		}
	}
	
	@Override
	public double getMaxHP() {
		return myMaxHP;
	}
	
	public void incrementCurrentJumps() {
	    myCurrentJumps++;
	}
	
	public void resetCurrentJumps(){
	    myCurrentJumps=0;
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
		return myDead || myCurrentHP <= 0;
	}
	/* ---IMortal Implementations End--- */
	
	@Override
	public Weapon getCurrentWeapon() {
		ExceptionThrower.notYetSupported();
		return myCurrentWeapon;
	}

	@Override
	public void setCurrentWeapon(Weapon currentWeapon) {
		ExceptionThrower.notYetSupported();
		myCurrentWeapon = currentWeapon;
	}

}
