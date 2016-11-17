package game_object.block;

import java.util.ArrayList;

import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.ImageStyle;

public abstract class AbstractBlock extends AbstractSprite implements IBlock {

	Dimension myDimension;
	BlockCollisionBehavior myCollisionBehavior;
	boolean myEffective; // effective for collision checking

	protected AbstractBlock(double x, double y, ArrayList<String> imgPaths, BlockCollisionBehavior bcb) {
		super(x, y, imgPaths);
		myCollisionBehavior = bcb;
		myEffective = true;
		setImgStyle(ImageStyle.TILE);
	}

	@Override
	public void setDimension(Dimension d) {
		myDimension = d;
	}

	@Override
	public Dimension getDimension() {
		return myDimension;
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
