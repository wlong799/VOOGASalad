package game_engine.inputcontroller;

import game_object.character.ICharacter;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.weapon.Projectile;
import game_object.weapon.ProjectileModel;
import game_object.weapon.WeaponSprite;

public class ProjectileManager {
	
	public static Projectile addProjectile(ICharacter character) {
		WeaponSprite weapon = character.getCurrentWeapon();
		if (weapon == null || weapon.getModel() == null)
			return null; // currently no weapon
		ProjectileModel pm = weapon.getModel().getProjectileModel();

		Projectile p = pm.newProjectileInstance(
				new Position(character.getPosition().getX(),
						character.getPosition().getY() + character.getDimension().getHeight() / 3.0),
				new Dimension(10, 10));
		if (character.isFacingLeft()) {
			p.getVelocity().setXVelocity(-Math.abs(p.getVelocity().getXVelocity()));
		} else {
			p.getVelocity().setXVelocity(Math.abs(p.getVelocity().getXVelocity()));
		}
		return p;
	}
}
