package game_object.weapon;

import game_object.CollisionBody;
import game_object.PhysicsBody;
import game_object.Position;
import game_object.Velocity;

/**
 * this class represents a single projectile on screen
 * the projectile keeps a ProjectileModel object "for reference"
 * in addition, it has instantaneous both position and velocity 
 * for full acceleration-based simulation
 * @author Yilun
 *
 */
public class Projectile implements PhysicsBody, CollisionBody {

	// position
	private Position myPosition;
	// velocity
	private Velocity myVelocity;
	
	private ProjectileModel myModel;

	public Projectile(double x, double y, double vx, double vy, ProjectileModel model) {
		myPosition = new Position(x, y);
		myVelocity = new Velocity(vx, vy);
		myModel = model;
	}
	
	public Position getPosition() {
		return myPosition;
	}

	public void setPosition(Position position) {
		myPosition = position;
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
	
}
