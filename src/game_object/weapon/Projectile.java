package game_object.weapon;

import java.util.List;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_engine.physics.ConstantStrategy;
import game_engine.physics.IPhysicsStrategy;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.character.ICharacter;
import game_object.constants.DefaultConstants;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;
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
	
	private static final long serialVersionUID = 2892891123139342664L;
	private final static Dimension MISSILE_DIMENSION = new Dimension(10, 10);
	private final static double HEIGHT_OFFSET_RATIO = 0.2;

	private ProjectileModel myModel;
	private ICharacter myParent;

	public Projectile(ICharacter character, Position position, List<String> imagePaths, ProjectileModel model) {
		super(position, MISSILE_DIMENSION, imagePaths);
		myParent = character;
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
	
	public ICharacter getParent() {
		return myParent;
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
	public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection) {
	    if (otherBody != myParent) {
	    	otherBody.onCollideWith(this, collisionDirection.opposite());
	    }
	}
	
	@Override
	public void onCollideWith(Enemy e, CollisionDirection collisionDirection) {
		if (e != myParent) {
			setValid(false);
		}
	}
	
	@Override
	public void onCollideWith(Hero h, CollisionDirection collisionDirection) {
		if (h != myParent) {
			setValid(false);
		}
	}

    @Override
    public IPhysicsStrategy getPhysics () {
        return new ConstantStrategy();
    }
    
    private void adjustPosition() {
    	// y position
		this.getPosition().setY(myParent.getPosition().getY()
				+ HEIGHT_OFFSET_RATIO * myParent.getDimension().getHeight());
		// x position
		if (myParent.isFacingLeft()) {
			this.getPosition().setX(myParent.getPosition().getX());
		} else {
			this.getPosition().setX(
					myParent.getPosition().getX()
					+ myParent.getDimension().getWidth() - this.getDimension().getWidth());
		}
		// for scrolling
		this.setScrollOffset(myParent.getScrollOffset());
	}
    
    private void setVelocityDirection() {
    	if (myParent.isFacingLeft()) {
			this.getVelocity().setXVelocity(-Math.abs(this.getVelocity().getXVelocity()));
		} else {
			this.getVelocity().setXVelocity(Math.abs(this.getVelocity().getXVelocity()));
		}
    }

    @Override
    public void onCollideWith (Projectile p, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
    }

}
