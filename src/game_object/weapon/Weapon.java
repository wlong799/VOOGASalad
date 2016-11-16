package game_object.weapon;

public abstract class Weapon {

	private double myDamage;
	private ProjectileModel myProjectileModel;
	
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
	
}
