package game_object.powerup;

import java.util.List;

import game_object.character.IUpgrader;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.weapon.WeaponModel;

public class NewWeaponPowerUp extends PowerUp {

	private WeaponModel myWeapon;
	private Dimension myDim;
	
	public NewWeaponPowerUp(Position position, Dimension powerUpIconDim, List<String> imagePaths, WeaponModel w, Dimension weaponDim) {
		super(position, powerUpIconDim, imagePaths);
		myWeapon = w;
		myDim = weaponDim;
	}
	
	@Override
	public void affect(IUpgrader u) {
		u.obtainWeapon(myWeapon, myDim);
	}

}
