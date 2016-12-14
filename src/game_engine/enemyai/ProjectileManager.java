package game_engine.enemyai;

import game_object.character.ICharacter;
import game_object.weapon.Projectile;
import game_object.weapon.ProjectileModel;
import game_object.weapon.Weapon;

/**
 * @author Charlie Wang
 *
 */
public class ProjectileManager {
	
	public static Projectile addProjectile(ICharacter character) {
		Weapon weapon = character.getCurrentWeapon();
		if (weapon == null || weapon.getModel() == null) {
			return null; // currently no weapon
		}
		ProjectileModel pm = weapon.getModel().getProjectileModel();
		Projectile p = pm.newProjectileInstance(character);
		return p;
	}
}
