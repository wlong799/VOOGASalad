package game_object.weapon;

/**
 * this class represents a single projectile on screen
 * the projectile keeps a ProjectileModel object "for reference"
 * in addition, it has instantaneous both position and velocity 
 * for full acceleration-based simulation
 * @author Yilun
 *
 */
public class Projectile {

	// position
	private double myX;
	private double myY;
	// velocity
	private double myVx;
	private double myVy;
	
	private ProjectileModel myModel;

	public Projectile(double x, double y, double vx, double vy, ProjectileModel model) {
		myX = x;
		myY = y;
		myVx = vx;
		myVy = vy;
		myModel = model;
	}

	public double getX() {
		return myX;
	}

	public void setX(double x) {
		myX = x;
	}

	public double getY() {
		return myY;
	}

	public void setY(double y) {
		myY = y;
	}

	public double getVx() {
		return myVx;
	}

	public void setVx(double vx) {
		myVx = vx;
	}

	public double getVy() {
		return myVy;
	}

	public void setVy(double vy) {
		myVy = vy;
	}

	public ProjectileModel getModel() {
		return myModel;
	}

	public void setModel(ProjectileModel model) {
		myModel = model;
	}
	
}
