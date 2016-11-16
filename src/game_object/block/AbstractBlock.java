package game_object.block;

import java.util.ArrayList;

import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.ImageStyle;

public abstract class AbstractBlock extends AbstractSprite {

	Dimension myDimension;
	BlockCollisionBehavior myCollisionBehavior;
	boolean myEffective; // effective for collision checking

	public AbstractBlock(double x, double y, ArrayList<String> imgPaths, BlockCollisionBehavior bcb) {
		super(x, y, imgPaths);
		myCollisionBehavior = bcb;
		myEffective = true;
		setImgStyle(ImageStyle.TILE);
	}

	
	public void setDimension(Dimension d) {
		myDimension = d;
	}

	public Dimension getDimension() {
		return myDimension;
	}

	public BlockCollisionBehavior getCollisionBehavior() {
		return myCollisionBehavior;
	}

	public void setCollisionBehavior(BlockCollisionBehavior collisionBehavior) {
		this.myCollisionBehavior = collisionBehavior;
	}
	
	public boolean isEffective() {
		return myEffective;
	}
	
	public void setEffective(boolean effective) {
		myEffective = effective;
	}

}
