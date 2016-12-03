package game_object.weapon;

public class Weapon {

	private double myDamage;
	private ProjectileModel myProjectileModel;
	private int myCollisionBitMask;
	
	public Weapon(double damage, ProjectileModel model, int collisionBitMask) {
		myDamage = damage;
		myProjectileModel = model;
		myCollisionBitMask = collisionBitMask;
		myProjectileModel.setCollisionBitMask(collisionBitMask);
	}
	
	public double getDamage() {
		return myDamage;
	}
	public void setDamage(double damage) {
		myDamage = damage;
	}
	public ProjectileModel getProjectileModel() {
		return myProjectileModel;
	}
	public void setProjectileModel(ProjectileModel projectileModel) {
		myProjectileModel = projectileModel;
	}

	public int getCollisionBitMask() {
		return myCollisionBitMask;
	}

	public void setCollisionBitMask(int collisionBitMask) {
		myCollisionBitMask = collisionBitMask;
	}

	
}
