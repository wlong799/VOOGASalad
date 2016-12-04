package game_object.weapon;

import game_object.core.Position;

import java.util.List;

import game_object.core.Dimension;
import game_object.core.Velocity;

/**
 * the ProjectileModel class represents a projectile model of a weapon
 * this class does NOT represent a single projectile on the screen. 
 * that is Projectile class (which has current velocity, direction, etc.)
 * @author Yilun
 *
 */
public class ProjectileModel {

	private List<String> myImagePaths;
	private Velocity myInitalVelocity;
	// is the projectile subject to gravity (so that the path will be a parabola)
	private boolean myAffectedByGravity;
	// is the projectile following hero? 
	private boolean myFollowHero;
	private int myCollisionBitMask;
	
	public ProjectileModel(List<String> imagePaths, Velocity v, boolean affectedByGravity) {
		myImagePaths = imagePaths;
		myInitalVelocity = v;
		myAffectedByGravity = affectedByGravity;
		myFollowHero = false;
	}
	
	public ProjectileModel(List<String> imgPaths, Velocity v, boolean affectedByGravity, boolean followHero) {
		myImagePaths = imgPaths;
		myInitalVelocity = v;
		myAffectedByGravity = affectedByGravity;
		myFollowHero = followHero;
	}

	public List<String> getImagePaths() {
		return myImagePaths;
	}

	public void setImagePaths(List<String> imgPaths) {
		myImagePaths = imgPaths;
	}

	public Velocity getInitalVelocity() {
		return myInitalVelocity;
	}

	public void setInitalVelocity(Velocity initalVelocity) {
		myInitalVelocity = initalVelocity;
	}

	public boolean isAffectedByGravity() {
		return myAffectedByGravity;
	}

	public void setAffectedByGravity(boolean affectedByGravity) {
		myAffectedByGravity = affectedByGravity;
	}

	public boolean isFollowHero() {
		return myFollowHero;
	}

	public void setFollowHero(boolean followHero) {
		myFollowHero = followHero;
	}

	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}

	public void setCollisionBitMask(int collisionBitMask) {
		myCollisionBitMask = collisionBitMask;
	}
	
	public Projectile newProjectileInstance(Position position, Dimension dimension) {
		return new Projectile(position, dimension, myImagePaths, this);
	}
	
}
