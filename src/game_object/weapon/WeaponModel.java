package game_object.weapon;

import game_object.character.*;
import game_object.constants.GameObjectConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;

import java.util.*;

public class WeaponModel {

	private static final double DEFAULT_RPOJECTILE_VELOCITY = 80;
	private double myDamage;
	private ProjectileModel myProjectileModel;
	private int myCollisionBitMask;
	private List<String> myImagePaths;
	
	public WeaponModel(List<String> imagePaths, double damage, ProjectileModel model, int collisionBitMask) {
		myImagePaths = imagePaths;
		myDamage = damage;
		myProjectileModel = model;
		myCollisionBitMask = collisionBitMask;
		myProjectileModel.setCollisionBitMask(collisionBitMask);
	}
	
	static public WeaponModel generateDefaultWeaponModel() {
		ArrayList<String> blueGunImgs = new ArrayList<>();
		ArrayList<String> bulletImgs = new ArrayList<>();
        blueGunImgs.add(GameObjectConstants.BLUE_GUN_WEAPON_RIGHT_FILE);
        blueGunImgs.add(GameObjectConstants.BLUE_GUN_WEAPON_LEFT_FILE);
		bulletImgs.add(GameObjectConstants.ORANGE_BULLET_FILE);
		ProjectileModel enemybulletModel = 
			new ProjectileModel(bulletImgs, new Velocity(DEFAULT_RPOJECTILE_VELOCITY, 0), false, false);
		return new WeaponModel(blueGunImgs, 0, enemybulletModel, 0);
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
	
	public Weapon newWeaponInstance(ICharacter character, Dimension dim) {
		double dz = 1e-5; // just one layer in front
		double x = character.getWeaponX();
		double y = character.getWeaponY();
		double z = character.getPosition().getZ();
		return new Weapon(new Position(x, y, z+dz), dim, myImagePaths, this);
	}

}
