package game_object.weapon;

import java.util.List;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_engine.physics.ConstantStrategy;
import game_engine.physics.IPhysicsStrategy;
import game_object.character.ICharacter;
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
 * @author Yilun, Bill Yu
 *
 */

public class Projectile extends AbstractSprite {
	
	private final static Dimension MISSILE_DIMENSION = new Dimension(10, 10);
	private final static double HEIGHT_OFFSET_RATIO = 0.2;

	private Velocity myVelocity;
	private ProjectileModel myModel;
	private ICharacter myFather;

	public Projectile(ICharacter character, Position position, List<String> imagePaths, ProjectileModel model) {
		super(position, MISSILE_DIMENSION, imagePaths);
		myFather = character;
		myModel = model;
		myVelocity = myModel.getInitalVelocity();
		myCategoryBitMask = DefaultConstants.PROJECTILE_CATEGORY_BIT_MASK;
		myCollisionBitMask = model.getCollisionBitMask();
		myPosition.setZ(Double.MAX_VALUE);
		adjustPosition();
		setVelocityDirection();
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
	public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection) {
		// TODO Auto-generated method stub
	    if(otherBody != myFather)
		otherBody.onCollideWith(this, collisionDirection.opposite());
	}

    @Override
    public IPhysicsStrategy getPhysics () {
        return new ConstantStrategy();
    }
    
    private void adjustPosition() {
    	// y position
		this.getPosition().setY(myFather.getPosition().getY()
				+ HEIGHT_OFFSET_RATIO * myFather.getDimension().getHeight());
		// x position
		if (myFather.isFacingLeft()) {
			this.getPosition().setX(myFather.getPosition().getX());
		} else {
			this.getPosition().setX(
					myFather.getPosition().getX()
					+ myFather.getDimension().getWidth() - this.getDimension().getWidth());
		}
		// for scrolling
		this.setScrollOffset(myFather.getScrollOffset());
	}
    
    private void setVelocityDirection() {
    	if (myFather.isFacingLeft()) {
			this.getVelocity().setXVelocity(-Math.abs(this.getVelocity().getXVelocity()));
		} else {
			this.getVelocity().setXVelocity(Math.abs(this.getVelocity().getXVelocity()));
		}
    }

}
