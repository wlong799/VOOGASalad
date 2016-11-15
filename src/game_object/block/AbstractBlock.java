package game_object.block;

import java.util.ArrayList;

import game_object.AbstractSprite;
import game_object.CollisionBody;
import game_object.Dimension;
import game_object.Position;

public class AbstractBlock extends AbstractSprite implements CollisionBody {

	Position myPos;
	ArrayList<String> myImgPaths;
	Dimension myDimension;
	BlockCollisionBehavior myCollisionBehavior;
	boolean myEffective; // effective for collision checking
	
	public void setPosition(Position pos) {
		myPos = pos;
	}

	public Position getPosition() {
		return myPos;
	}

	public ArrayList<String> getImagePaths() {
		return myImgPaths;
	}

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

	@Override
	public void collided(CollisionBody otherBody) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCategoryBitMask(int categoryBitMask) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int getCategoryBitMask() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCollisionBitMask() {
		// TODO Auto-generated method stub
		return 0;
	}

}
