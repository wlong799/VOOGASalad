package game_object.character;

import java.util.ArrayList;

import game_object.DefaultConstants;
import game_object.CollisionBody;
import game_object.acting.ActionTrigger;

public class Hero extends ActiveCharacter {

	private int myCategoryBitMask = DefaultConstants.HERO_CATEGORY_BIT_MASK;
	private int myCollisionBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;

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
		if (!myAffectedByPhysics || myCollisionBitMask == 0) {
			return;
		}
		if (
			otherBody.getCategoryBitMask() == DefaultConstants.ENEMY_CATEGORY_BIT_MASK
			&& (myCollisionBitMask | DefaultConstants.ENEMY_CATEGORY_BIT_MASK) != 0
		) {
			assert(otherBody instanceof Enemy);
			Enemy enemy = (Enemy) otherBody;
			myCurrentHP -= enemy.getBodyDamage();
		}
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
