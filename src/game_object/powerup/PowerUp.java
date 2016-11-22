package game_object.powerup;

import java.util.List;

import game_object.character.IUpgrader;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.ICollisionBody;

public abstract class PowerUp extends AbstractSprite {
	
	protected PowerUp(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
	}

	public abstract void affect(IUpgrader u);

	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getAffectedByPhysics() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Velocity getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVelocity(Velocity velocity) {
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

	@Override
	public void onCollideWith(ICollisionBody otherBody) {
		// TODO Auto-generated method stub
		
	}
	
}
