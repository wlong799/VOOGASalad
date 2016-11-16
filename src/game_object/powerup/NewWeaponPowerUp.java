package game_object.powerup;

import game_object.character.Upgrader;
import game_object.weapon.Weapon;
import java.util.*;

public class NewWeaponPowerUp extends PowerUp {

	private Weapon myWeapon;
	
	public NewWeaponPowerUp(double x, double y, ArrayList<String> imgPaths, Weapon w) {
		super(x, y, imgPaths);
		myWeapon = w;
	}
	
	@Override
	public void affect(Upgrader u) {
		u.getWeapon(myWeapon);
	}

}
