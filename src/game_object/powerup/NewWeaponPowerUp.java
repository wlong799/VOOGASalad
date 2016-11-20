package game_object.powerup;

import java.util.List;

import game_object.character.IUpgrader;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.weapon.Weapon;

public class NewWeaponPowerUp extends PowerUp {

	private Weapon myWeapon;
	
	protected NewWeaponPowerUp(Position position, Dimension dimension, List<String> imagePaths, Weapon w) {
		super(position, dimension, imagePaths);
		myWeapon = w;
	}
	
	@Override
	public void affect(IUpgrader u) {
		u.obtainWeapon(myWeapon);
	}

}
