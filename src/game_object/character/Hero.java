package game_object.character;

import java.util.ArrayList;
import java.util.List;

import game_object.acting.ActionTrigger;
import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;
import game_object.weapon.Weapon;

public class Hero extends AbstractCharacter implements IUpgrader, ITriggerable {

	private final int myCategoryBitMask = DefaultConstants.HERO_CATEGORY_BIT_MASK;
	private final int myCollisionBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
	private boolean hasProjectile = false;

	public Hero(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
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

	@Override
	public void onCollideWith(ICollisionBody otherBody) {
		if (otherBody.getCategoryBitMask() == DefaultConstants.ENEMY_CATEGORY_BIT_MASK) {
			assert(otherBody instanceof Enemy);
			//Enemy enemy = (Enemy) otherBody;
			getVelocity().setYVelocity(-100);
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
	public void obtainWeapon(Weapon w) {
		setCurrentWeapon(w);
	}

	@Override
	public void speedUp(double percent) {
		setMovingUnit(getMovingUnit()*(1+percent));
	}
	
        public void changeSize(double multiplier) {
            myDimension.setHeight(multiplier*myDimension.getHeight());
            myDimension.setWidth(multiplier*myDimension.getWidth());
        }	
        
        public void setHasProjectile() {
            hasProjectile = true;
        }
        
        
	
}
