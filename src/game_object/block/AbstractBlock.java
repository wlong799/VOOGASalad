package game_object.block;

import java.util.List;

import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.ImageStyle;
import game_object.core.Position;

abstract class AbstractBlock extends AbstractSprite implements IBlock {

	protected BlockCollisionBehavior myCollisionBehavior = BlockCollisionBehavior.ALL_ALL_COLLISION;
	
	protected AbstractBlock(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myImageStyle = ImageStyle.TILE;
	}

	@Override
	public BlockCollisionBehavior getCollisionBehavior() {
		return myCollisionBehavior;
	}

	@Override
	public void setCollisionBehavior(BlockCollisionBehavior collisionBehavior) {
		this.myCollisionBehavior = collisionBehavior;
	}
	
}
