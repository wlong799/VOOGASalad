package game_object.block;

import game_object.CollisionBody;
import game_object.DefaultConstants;

public class GroundBlock extends AbstractBlock {
	
	private int myCategoryBitMask = DefaultConstants.GROUND_BLOCK_CATEGORY_BIT_MASK;
	private int myCollisionBitMask;
	
	@Override
	public void collided(CollisionBody otherBody) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setCategoryBitMask(int categoryBitMask) {
		myCategoryBitMask = categoryBitMask;
	}
	
	@Override
	public int getCategoryBitMask() {
		return myCategoryBitMask;
	}

	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		myCollisionBitMask = collisionBitMask;
	}

	@Override
	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}
	
}
