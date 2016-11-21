package game_object.character;

import java.util.ArrayList;
import java.util.List;

import game_object.acting.ActionTrigger;
import game_object.core.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;

public class Enemy extends ActiveCharacter {

	private int myCategoryBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
	private int myCollisionBitMask =
			DefaultConstants.HERO_CATEGORY_BIT_MASK |
			DefaultConstants.BLOCK_CATEGORY_BIT_MASK;
	// damage this enemy does to the hero if directly collided.
	private double myBodyDamage = DefaultConstants.ENEMY_BODY_DAMAGE;
	
	public Enemy(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
	}

	public void setBodyDamage(double bodyDamage) {
		myBodyDamage = bodyDamage;
	}
	
	public double getBodyDamage() {
		return myBodyDamage;
	}
	
	@Override
	public void shoot() {
		ExceptionThrower.notYetSupported();
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
