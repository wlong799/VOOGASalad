package game_object.weapon;

import game_object.core.Velocity;

/**
 * the ProjectileModel class represents a projectile model of a weapon
 * this class does NOT represent a single projectile on the screen. 
 * that is Projectile class (which has current velocity, direction, etc.)
 * @author Yilun
 *
 */
public class ProjectileModel {

	private String myImgPath;
	private Velocity myInitalVelocity;
	// is the projectile subject to gravity (so that the path will be a parabola)
	private boolean myAffectedByGravity;
	
	public ProjectileModel(String imgPath, Velocity v, boolean affectedByGravity) {
		myImgPath = imgPath;
		myInitalVelocity = v;
		myAffectedByGravity = affectedByGravity;
	}

	public String getImgPath() {
		return myImgPath;
	}

	public void setImgPath(String imgPath) {
		myImgPath = imgPath;
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
	
	
	
}
