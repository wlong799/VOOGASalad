package authoring.view.inspector.settings;

import game_object.constants.DefaultConstants;
import game_object.core.ISprite;

public class ComponentPhysicsSettings {
	private ISprite mySprite;

	public ComponentPhysicsSettings(ISprite spriteToSet) {
		mySprite = spriteToSet;
	}
	
	public void makePhysicsApplicable(boolean applyPhysics) {
		mySprite.setAffectedByPhysics(applyPhysics);
	}

	public void setCollisionSettingWithHeros(boolean shouldRegisterCollision) {
		setCollisionBitMask(DefaultConstants.HERO_CATEGORY_BIT_MASK, shouldRegisterCollision);
	}
	
	public void setCollisionSettingWithEnemies(boolean shouldRegisterCollision) {
		setCollisionBitMask(DefaultConstants.ENEMY_CATEGORY_BIT_MASK, shouldRegisterCollision);
	}
	
	public void setCollisionSettingWithBlock(boolean shouldRegisterCollision) {
		setCollisionBitMask(DefaultConstants.BLOCK_CATEGORY_BIT_MASK, shouldRegisterCollision);
	}
	
	private void setCollisionBitMask(int appropriateBitMask, boolean shouldRegisterCollision) {
		int collisionBitMask = shouldRegisterCollision ? 
				mySprite.getCollisionBitMask() | appropriateBitMask
				: mySprite.getCollisionBitMask() & ~appropriateBitMask;
		mySprite.setCollisionBitMask(collisionBitMask);
	}
}
