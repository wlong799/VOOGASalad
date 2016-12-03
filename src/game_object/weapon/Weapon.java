package game_object.weapon;

public class Weapon {

	private double myDamage;
	private ProjectileModel myProjectileModel;
	private WeaponSide mySide;
	
	public Weapon(double damage, ProjectileModel model, WeaponSide side) {
		assert !(model.isFollowHero() && side.equals(WeaponSide.HERO_SIDE)) : "Follow Hero weapon must be ENEMY_SIDE";
		myDamage = damage;
		myProjectileModel = model;
		mySide = side;
		myProjectileModel.setSide(mySide);
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

	public WeaponSide getSide() {
		return mySide;
	}

	public void setSide(WeaponSide side) {
		mySide = side;
	}
	
}
