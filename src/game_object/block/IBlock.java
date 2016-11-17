package game_object.block;

import game_object.core.ISprite;

public interface IBlock extends ISprite {
	
	BlockCollisionBehavior getCollisionBehavior();

	void setCollisionBehavior(BlockCollisionBehavior collisionBehavior);
	
	boolean isEffective();
	
	void setEffective(boolean effective);

}
