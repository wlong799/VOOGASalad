package game_object.block;

import java.util.ArrayList;

import game_object.core.DefaultConstants;
import game_object.core.ExceptionThrower;
import game_object.core.Velocity;
import game_object.simulation.ICollisionBody;

public class StaticBlock extends AbstractBlock {
	
	private final int myCategoryBitMask = DefaultConstants.BLOCK_CATEGORY_BIT_MASK;
	
	public StaticBlock(double x, double y, ArrayList<String> imgPaths) {
		super(x, y, imgPaths, BlockCollisionBehavior.TOP_TOP_COLLISION);
	}

	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		if (affectedByPhysics) {
			throw new IllegalArgumentException("StaticBlock shouldn't be affected by physics");
		}
	}

	@Override
	public boolean getAffectedByPhysics() {
		return false;
	}

	@Override
	public Velocity getVelocity() {
		return null;
	}

	@Override
	public void setVelocity(Velocity velocity) {
		if (velocity != null) {
			throw new IllegalArgumentException("StaticBlock shouldn't be set a velocity");
		}
	}

	@Override
	public int getCategoryBitMask() {
		return myCategoryBitMask;
	}

	@Override
	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}

	@Override
	public void onCollideWith(ICollisionBody otherBody) {
		ExceptionThrower.notYetSupported();
	}
}
