package game_object.character;

import java.util.List;
import game_engine.collision.CollisionEngine.CollisionDirection;
import game_engine.physics.GravityFrictionStrategy;
import game_object.block.Block;
import game_object.collision.MotionCollisionStrategy;
import game_object.constants.DefaultConstants;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.weapon.Weapon;

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
	protected Weapon myCurrentWeapon;
	protected boolean myJumping;
	protected boolean myTempJumping;
	protected boolean myShooting;
	protected boolean myTempShooting;
	protected MotionCollisionStrategy<ICharacter, Block> myStrategyOnCollisionWithBlock;
	
	// the following two fields define the weapon-holding position
	// the weapon will be relatively fixed at characterPosition + weaponDisplacement
	private double myWeaponDisplacementX;
	private double myWeaponDisplacementY;

	protected AbstractCharacter(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myAffectedByPhysics = true;
		myPhysicsStrategy = new GravityFrictionStrategy();
		myStrategyOnCollisionWithBlock = new MotionCollisionStrategy<>();
		myWeaponDisplacementX = dimension.getWidth();
		myWeaponDisplacementY = 0;
	}
	
	/*ICollisionBody Implementation*/
	@Override
	public MotionCollisionStrategy<ICharacter, Block> getStrategyOnCollisionWithBlock() {
		return myStrategyOnCollisionWithBlock;
	}
	
	@Override
	public void onCollideWith(Block b, CollisionDirection collisionDirection){
		getStrategyOnCollisionWithBlock().applyCollision(this, b, collisionDirection);
	}
	/* ---ICollisionBody Implementation END--- */


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
		if (currentHP < 0) {
			setValid(false);
		}
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

	/* IMover Implementations */
	@Override
	public double getMovingUnit() {
		return myMovingUnit;
	}

	@Override
	public void setMovingUnit(double movingUnit) {
		myMovingUnit = movingUnit;
	}

	@Override
	public double getJumpingUnit() {
		return myJumpingUnit;
	}

	@Override
	public void setJumpingUnit(double jumpingUnit) {
		myJumpingUnit = jumpingUnit;
	}
	
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
	}

	@Override
	public void moveDown() {
	}

	@Override
	public void shoot() {
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

	@Override
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
	@Override
	public Weapon getCurrentWeapon() {
		return myCurrentWeapon;
	}

	@Override
	public void setCurrentWeapon(Weapon currentWeapon) {
		if (myCurrentWeapon != null) {
			myCurrentWeapon.setOwner(null);
			myChildSprites.removeSprite(myCurrentWeapon);
		}
		myCurrentWeapon = currentWeapon;
		myCurrentWeapon.setOwner(this);
		myChildSprites.addSprite(myCurrentWeapon);
	}
	
	@Override
	public double getWeaponX() {
		if(myCurrentWeapon==null) // no weapon
			return myPosition.getX();
		if(!myFacingLeft) {
			return myPosition.getX()+myWeaponDisplacementX;
		} else {
			double diffX = myCurrentWeapon.getDimension().getHeight()-(myDimension.getWidth()-myWeaponDisplacementX);
			return myPosition.getX()-diffX;
		}
	}

	@Override
	public void setWeaponDisplacementX(double weaponDisplacementX) {
		myWeaponDisplacementX = weaponDisplacementX;
	}

	@Override
	public double getWeaponY() {
		if(myCurrentWeapon==null) // no weapon
			return myPosition.getY();
		return myWeaponDisplacementY + myPosition.getY();
	}

	@Override
	public void setWeaponDisplacementY(double weaponDisplacementY) {
		myWeaponDisplacementY = weaponDisplacementY;
	}
	
	/* IMover's InputController part Implementations */	
	public boolean isJumping() {
		return myJumping;
	}
	
	public void setJumping(boolean jumping) {
		myJumping = jumping;
	}
	
	public boolean isTempJumping() {
		return myTempJumping;
	}
	
	public void setTempJumping(boolean jumping) {
		myTempJumping = jumping;
	}

	public boolean isShooting() {
		return myShooting;
	}
	
	public void setShooting(boolean Shooting) {
		myShooting = Shooting;
	}
	
	public boolean isTempShooting() {
		return myTempShooting;
	}
	
	public void setTempShooting(boolean Shooting) {
		myTempShooting = Shooting;
	}
	
	/* InputController Implementations END*/
}
