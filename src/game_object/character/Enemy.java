package game_object.character;

import java.util.ArrayList;

import game_object.acting.ActionTrigger;
import game_object.core.DefaultConstants;
import game_object.core.ExceptionThrower;
import game_object.simulation.ICollisionBody;

public class Enemy extends ActiveCharacter {
	
	private int myCategoryBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
	private int myCollisionBitMask =
			DefaultConstants.HERO_CATEGORY_BIT_MASK |
			DefaultConstants.BLOCK_CATEGORY_BIT_MASK;
	private double myBodyDamage = 30; // damage this enemy does to the hero if directly collided.
	
	
	public Enemy(double x, double y, ArrayList<String> imgPaths, double maxHP) {
		super(x, y, imgPaths, maxHP);
	}

	public void setBodyDamage(double bodyDamage) {
		myBodyDamage = bodyDamage;
	}
	
	public double getBodyDamage() {
		return myBodyDamage;
	}
	
	@Override
	public ArrayList<ActionTrigger> getActionTriggers() {
		ExceptionThrower.notYetSupported();
		return null;
	}

	@Override
	public void setActionTriggers(ArrayList<ActionTrigger> ats) {
		ExceptionThrower.notYetSupported();
	}

	/* ICollisionBody Getter Implementations */
	@Override
	public int getCategoryBitMask() {
		return myCategoryBitMask;
	}

	@Override
	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}

	@Override
	public void onCollideWith(ICollisionBody otherSprite) {
		ExceptionThrower.notYetSupported();
	}
	
}
