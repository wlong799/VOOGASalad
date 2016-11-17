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
	private double bodyDamage; // damage this enemy does to the hero if directly collided.
	
	
	public Enemy(double x, double y, ArrayList<String> imgPaths, double maxHP) {
		super(x, y, imgPaths, maxHP);
	}

	public double getBodyDamage() {
		return bodyDamage;
	}
	
	@Override
	public ArrayList<ActionTrigger> getActionTriggers() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented");
	}

	@Override
	public void setActionTriggers(ArrayList<ActionTrigger> ats) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented");
	}

	/* CollisionBody */
	@Override
	public void setCategoryBitMask(int categoryBitMask) {
		ExceptionThrower.notYetSupported();
	}
	
	@Override
	public int getCategoryBitMask() {
		return myCategoryBitMask;
	}

	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		ExceptionThrower.notYetSupported();
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
