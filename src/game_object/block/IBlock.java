package game_object.block;

import game_object.core.Dimension;
import game_object.core.ISprite;

public interface IBlock extends ISprite {
	
	void setDimension(Dimension d);

	Dimension getDimension();

	BlockCollisionBehavior getCollisionBehavior();

	void setCollisionBehavior(BlockCollisionBehavior collisionBehavior);
	
	boolean isEffective();
	
	void setEffective(boolean effective);

}
