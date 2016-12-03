package game_object.weapon;

import java.util.List;

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
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented");
	}

	@Override
	public int getCategoryBitMask() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented");
	}

	@Override
	public void setCollisionBitMask(int collisionBitMask) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented");
	}

	@Override
	public int getCollisionBitMask() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented");
	}

	@Override
	public void onCollideWith(ICollisionBody otherBody) {
		// TODO Auto-generated method stub
		
	}

	
}
