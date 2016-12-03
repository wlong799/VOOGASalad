package game_object.weapon;

import java.util.List;

import game_object.constants.DefaultConstants;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.ICollisionBody;

/**
 * this class represents a single projectile on screen
 * the projectile keeps a ProjectileModel object "for reference"
 * in addition, it has instantaneous both position and velocity 
 * for full acceleration-based simulation
 * @author Yilun
 *
 */

public class Projectile extends AbstractSprite {

	// velocity
	private Velocity myVelocity;
	
	private ProjectileModel myModel;


	public Projectile(Position position, Dimension dimension, List<String> imagePaths, ProjectileModel model) {
		super(position, dimension, imagePaths);
		myModel = model;
		myVelocity = myModel.getInitalVelocity();
		myCategoryBitMask = DefaultConstants.PROJECTILE_CATEGORY_BIT_MASK;
		myCollisionBitMask = model.getCollisionBitMask();
	}
	
	public ProjectileModel getModel() {
		return myModel;
	}

	public void setModel(ProjectileModel model) {
		myModel = model;
	}

	@Override
	public void setAffectedByPhysics(boolean affectedByPhysics) {
		myModel.setAffectedByGravity(affectedByPhysics);
	}

	@Override
	public boolean getAffectedByPhysics() {
		return myModel.isAffectedByGravity();
	}

	@Override
	public Velocity getVelocity() {
		return myVelocity;
	}

	@Override
	public void setVelocity(Velocity velocity) {
		myVelocity = velocity;
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

	@Override
	public void onCollideWith(ICollisionBody otherBody) {
		// TODO Auto-generated method stub
		
	}

	
}
