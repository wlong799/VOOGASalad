package game_object.character;

import java.util.ArrayList;

import game_object.acting.ActionTrigger;
import game_object.core.DefaultConstants;
import game_object.simulation.ICollisionBody;
import game_object.weapon.Weapon;

public class Hero extends ActiveCharacter implements IUpgrader {

	private final int myCategoryBitMask = DefaultConstants.HERO_CATEGORY_BIT_MASK;
	private final int myCollisionBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;

	public Hero(double x, double y, ArrayList<String> imgPaths, double maxHP) {
		super(x, y, imgPaths, maxHP);
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
	public void onCollideWith(ICollisionBody otherBody) {
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
	
	/* CollisionBody Getter Implementations */
	@Override
	public int getCategoryBitMask() {
		return myCategoryBitMask;
	}

	@Override
	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}

	/* Upgrader -- Not used for now*/
	@Override
	public void replenishHealth() {
		setCurrentHP(getMaxHP());
	}

	@Override
	public void getWeapon(Weapon w) {
		setCurrentWeapon(w);
	}

	@Override
	public void speedUp(double percent) {
		setMovingUnit(getMovingUnit()*(1+percent));
	}
	
}
