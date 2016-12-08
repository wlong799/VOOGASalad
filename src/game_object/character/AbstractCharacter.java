package game_object.character;

import java.util.List;
import game_engine.collision.CollisionEngine.CollisionDirection;
import game_engine.physics.GravityFrictionStrategy;
import game_object.block.StaticBlock;
import game_object.constants.DefaultConstants;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.weapon.WeaponSprite;

/**
 * A base class for all characters.
 * @author Jay
 */
abstract class AbstractCharacter extends AbstractSprite implements ICharacter {

	protected double myMaxHP = DefaultConstants.CHARACTER_MAX_HP;
	protected double myCurrentHP = DefaultConstants.CHARACTER_MAX_HP;
	private double myMovingUnit = DefaultConstants.MOVING_UNIT;
	private double myJumpingUnit = DefaultConstants.JUMPING_UNIT;
	private int myMaxNumberOfJumps = DefaultConstants.MAX_JUMP;
	private Velocity myVelocity = new Velocity(0, 0);
	protected boolean myDead = false;
        protected int myCurrentJumps;
	protected WeaponSprite myCurrentWeapon;
	
	// the following two fields define the weapon-holding position
	// the weapon will be relatively fixed at characterPosition + weaponDisplacement
	private double myWeaponDisplacementX;
	private double myWeaponDisplacementY;
	
	protected AbstractCharacter(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myAffectedByPhysics = true;
		myPhysicsStrategy = new GravityFrictionStrategy();
		// default displacement
		myWeaponDisplacementX = dimension.getWidth();
		myWeaponDisplacementY = 0;
	}
	/*ICollisionBody Implementation*/
	
	@Override
	public void onCollideWith(StaticBlock b, CollisionDirection collisionDirection){
	    if (collisionDirection != CollisionDirection.NONE) {
	            
	            //c.onCollideWith(other);
	            //logSuppressed = false;
	            if (collisionDirection == CollisionDirection.TOP) {
	                getPosition().setY(b.getPosition().getY() - getDimension().getHeight());
	                getVelocity().setYVelocity(0);
	                resetCurrentJumps();
	            }
	            else if (collisionDirection == CollisionDirection.BOTTOM) {
	                getPosition().setY(b.getPosition()
	                        .getY() + b.getDimension().getHeight());
	                getVelocity().setYVelocity(0);
	            }
	            else if (collisionDirection == CollisionDirection.RIGHT) {
	                getPosition().setX(b.getPosition().getX() +
	                                     b.getDimension().getWidth());
	                getVelocity().setXVelocity(0);
	            }
	            else if (collisionDirection == CollisionDirection.LEFT) {
	                getPosition().setX(b.getPosition().getX() - getDimension().getWidth());
	                getVelocity().setXVelocity(0);
	            }
	            else {
	                // TODO: Implement corner collision handling
	            }

	        }
	}
	
	
	
	/* IMortal Implementations */
	@Override
	public void setMaxHP(int maxHP, boolean setCurrentHPtoMax) {
		myMaxHP = maxHP;
		if (setCurrentHPtoMax) {
			myCurrentHP = maxHP;
		}
	}
	
	@Override
	public double getMaxHP() {
		return myMaxHP;
	}
	
	@Override
	public void setCurrentHP(double currentHP) {
		myCurrentHP = currentHP;
	}
	
	@Override
	public double getCurrentHP() {
		return myCurrentHP;
	}
	
	@Override
	public void setDead(boolean dead) {
		myDead = dead;
	}

	@Override
	public boolean getDead() {
		return myDead || myCurrentHP <= 0;
	}
	/* ---IMortal Implementations End--- */
	
	@Override
	public WeaponSprite getCurrentWeapon() {
		return myCurrentWeapon;
	}

	@Override
	public void setCurrentWeapon(WeaponSprite currentWeapon) {
		myCurrentWeapon = currentWeapon;
	}
	
	public double getMovingUnit() {
		return myMovingUnit;
	}

	public void setMovingUnit(double movingUnit) {
		myMovingUnit = movingUnit;
	}

	public double getJumpingUnit() {
		return myJumpingUnit;
	}

	public void setJumpingUnit(double jumpingUnit) {
		myJumpingUnit = jumpingUnit;
	}

	/* IMover Implementations */
	@Override
	public void moveRight() {
		myVelocity.setXVelocity(myMovingUnit);
	}
	
	@Override
	public void moveLeft() {
		myVelocity.setXVelocity(-myMovingUnit);
	}

	@Override
	public void moveUp() {
		ExceptionThrower.notYetSupported();
	}

	@Override
	public void moveDown() {
		ExceptionThrower.notYetSupported();
	}

	@Override
	public void shoot() {
		ExceptionThrower.notYetSupported();
	}
	
	@Override
	public void jumpUp() { // jumping is simulated by given the sprite a upward (negative) velocity.
		if (myCurrentJumps < getMaxNumberOfJumps()) {
			myCurrentJumps++;
			myVelocity.setYVelocity(-myJumpingUnit);
		}
	}
	
	@Override
	public void resetCurrentJumps(){
	    myCurrentJumps = 0;
	}
	
	public void setMaxNumberOfJumps(int maxNumberOfJumps) {
		myMaxNumberOfJumps = maxNumberOfJumps;
	}
	
	@Override
	public int getMaxNumberOfJumps() {
		return myMaxNumberOfJumps;
	}
	/* ---IMover Implementations END---*/
	
	
	/* IPhysicsBody Implementations */		
	@Override
	public void setVelocity(Velocity velocity) {
		myVelocity = velocity;
	}
	
	@Override
	public Velocity getVelocity() {
		return myVelocity;
	}
	
	/* ---IPhysicsBody Implementations END--- */	

	public double getWeaponDisplacementX() {
		return myWeaponDisplacementX;
	}

	public void setWeaponDisplacementX(double weaponDisplacementX) {
		myWeaponDisplacementX = weaponDisplacementX;
	}

	public double getWeaponDisplacementY() {
		return myWeaponDisplacementY;
	}

	public void setWeaponDisplacementY(double weaponDisplacementY) {
		myWeaponDisplacementY = weaponDisplacementY;
	}

}
