package game_object.character;

import java.util.ArrayList;

import game_object.DefaultConstants;
import game_object.CollisionBody;
import game_object.acting.ActionTrigger;

public class Enemy extends ActiveCharacter {
	
	private int myCategoryBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
	private int myCollisionBitMask = DefaultConstants.HERO_CATEGORY_BIT_MASK;
	private double bodyDamage; // damage this enemy does to the hero if directly collided.
	
	public double getBodyDamage() {
		return bodyDamage;
	}
	
	@Override
	public ArrayList<ActionTrigger> getActionTriggers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActionTriggers(ArrayList<ActionTrigger> ats) {
		// TODO Auto-generated method stub
	}

	@Override
	public void collided(CollisionBody otherBody) {
		// TODO Auto-generated method stub
	}

	/* CollisionBody */
	@Override
	public void setCategoryBitMask(int categoryBitMask) {
		myCategoryBitMask = categoryBitMask;
	}
	
	@Override
	public int getCategoryBitMask() {
		return myCategoryBitMask;
	}

	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		myCollisionBitMask = collisionBitMask;
	}

	@Override
	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}
	
}
