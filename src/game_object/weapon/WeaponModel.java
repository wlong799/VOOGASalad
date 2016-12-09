package game_object.weapon;

import game_object.character.*;
import game_object.core.Dimension;
import game_object.core.Position;
import java.util.*;

public class WeaponModel {

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
		double dx = character.getWeaponDisplacementX();
		double dy = character.getWeaponDisplacementY();
		double dz = 1e-5; // just one layer in front
		double x = character.getPosition().getX();
		double y = character.getPosition().getY();
		double z = character.getPosition().getZ();
		return new Weapon(new Position(x+dx, y+dy, z+dz), dim, myImagePaths, this);
	}

}
