package game_object.block;

import java.util.List;

import game_object.core.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.ICollisionBody;

public class StaticBlock extends AbstractBlock {
	
	private final int myCategoryBitMask = DefaultConstants.BLOCK_CATEGORY_BIT_MASK;
	protected int myCollisionBitMask =
		DefaultConstants.HERO_CATEGORY_BIT_MASK |
		DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
	
	public StaticBlock(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
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
		return new Velocity(0, 0);
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
