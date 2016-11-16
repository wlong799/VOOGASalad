package game_object.weapon;

/**
 * the ProjectileModel class represents a projectile model of a weapon
 * this class does NOT represent a single projectile on the screen. 
 * that is Projectile class (which has current velocity, direction, etc.)
 * @author Yilun
 *
 */
public class ProjectileModel {

	private String myImgPath;
	private double mySpeed; // initial speed
	// initial angle, in degree, 0 is x-direction, positive goes **down** (positive y direction)
	private double myAngle; 
	// is the projectile subject to gravity (so that the path will be a parabola)
	private boolean myAffectedByGravity;
	
	public ProjectileModel(String imgPath, double speed, double angle, boolean affectedByGravity) {
		myImgPath = imgPath;
		mySpeed = speed;
		myAngle = angle;
		myAffectedByGravity = affectedByGravity;
	}

	public String getImgPath() {
		return myImgPath;
	}

	public void setImgPath(String imgPath) {
		myImgPath = imgPath;
	}

	public double getSpeed() {
		return mySpeed;
	}

	public void setSpeed(double speed) {
		mySpeed = speed;
	}

	public double getAngle() {
		return myAngle;
	}

	public void setAngle(double angle) {
		myAngle = angle;
	}

	public boolean isAffectedByGravity() {
		return myAffectedByGravity;
	}

	public void setAffectedByGravity(boolean affectedByGravity) {
		myAffectedByGravity = affectedByGravity;
	} 
	
	
	
}
