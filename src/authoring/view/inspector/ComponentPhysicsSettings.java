package authoring.view.inspector;

import game_object.constants.DefaultConstants;
import game_object.core.ISprite;

public class ComponentPhysicsSettings {
	private ISprite sprite;

	public ComponentPhysicsSettings(ISprite spriteToSet) {
		sprite = spriteToSet;
	}

	public void setCollisionOnHeros(boolean registerCollision) {
		int collisionBitMask = registerCollision ? 
				sprite.getCollisionBitMask() | DefaultConstants.HERO_CATEGORY_BIT_MASK
				: sprite.getCollisionBitMask() & ~DefaultConstants.HERO_CATEGORY_BIT_MASK;
		sprite.setCollisionBitMask(collisionBitMask);
	}
}
