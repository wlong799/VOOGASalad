package game_object.block;

import java.util.ArrayList;

import game_object.AbstractSprite;
import game_object.Dimension;
import game_object.Position;

public abstract class AbstractBlock extends AbstractSprite {

	Position myPos;
	ArrayList<String> myImgPaths;
	Dimension myDimension;
	BlockCollisionBehavior myCollisionBehavior;
	boolean myEffective; // effective for collision checking
	
	@Override
	public void setPosition(Position pos) {
		myPos = pos;
	}
	
	@Override
	public Position getPosition() {
		return myPos;
	}

	@Override
	public ArrayList<String> getImagePaths() {
		return myImgPaths;
	}

	@Override
	public void setImagePaths(ArrayList<String> imgPaths) {
		myImgPaths = imgPaths;
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
