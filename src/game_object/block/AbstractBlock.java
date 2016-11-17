package game_object.block;

import java.util.ArrayList;

import game_object.core.AbstractSprite;
import game_object.core.DefaultConstants;
import game_object.core.ImageStyle;

abstract class AbstractBlock extends AbstractSprite implements IBlock {

	protected int myCollisionBitMask =
			DefaultConstants.HERO_CATEGORY_BIT_MASK |
			DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
	protected BlockCollisionBehavior myCollisionBehavior;
	boolean myEffective; // effective for collision checking

	protected AbstractBlock(double x, double y, ArrayList<String> imgPaths, BlockCollisionBehavior bcb) {
		super(x, y, imgPaths);
		myCollisionBehavior = bcb;
		myEffective = true;
		setImgStyle(ImageStyle.TILE);
	}

	@Override
	public BlockCollisionBehavior getCollisionBehavior() {
		return myCollisionBehavior;
	}

	@Override
	public void setCollisionBehavior(BlockCollisionBehavior collisionBehavior) {
		this.myCollisionBehavior = collisionBehavior;
	}
	
	@Override
	public boolean isEffective() {
		return myEffective;
	}
	
	@Override
	public void setEffective(boolean effective) {
		myEffective = effective;
	}

}
