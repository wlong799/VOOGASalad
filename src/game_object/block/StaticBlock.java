package game_object.block;

import java.util.List;

import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;

public class StaticBlock extends AbstractBlock {
	
	public StaticBlock(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myCategoryBitMask = DefaultConstants.BLOCK_CATEGORY_BIT_MASK;
		myCollisionBitMask =
				DefaultConstants.HERO_CATEGORY_BIT_MASK |
				DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
		myAffectedByPhysics = false;
	}

	@Override
	public void onCollideWith(ICollisionBody otherBody) {
		//TODO: 
	}

}
