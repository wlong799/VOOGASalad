package game_object.block;

import game_object.core.Dimension;

public interface IBlock {
	
	void setDimension(Dimension d);

	Dimension getDimension();

	BlockCollisionBehavior getCollisionBehavior();

	void setCollisionBehavior(BlockCollisionBehavior collisionBehavior);
	
	boolean isEffective();
	
	void setEffective(boolean effective);

}
