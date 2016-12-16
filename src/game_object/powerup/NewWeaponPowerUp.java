package game_object.powerup;

import java.util.List;

import game_object.character.IUpgrader;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.weapon.WeaponModel;

/**
 * A powerup that will give character a new weapon
 * @author Jay, Yilun
 */
public class NewWeaponPowerUp extends AbstractPowerUp {

	private static final long serialVersionUID = 2114906066615238333L;
	private static final double DEFAULT_WEAPON_WIDTH = 20;
	private static final double DEFAULT_WEAPON_HEIGHT = 20;
	

	private WeaponModel myWeaponModel;
	private Dimension myWeaponDimension;
	
	public NewWeaponPowerUp(
		Position position,
		Dimension powerUpIconDimension,
		List<String> imagePaths
	) {
		super(position, powerUpIconDimension, imagePaths);
		initDefaultModel();
	}
	
	private void initDefaultModel() {
		myWeaponModel = WeaponModel.generateDefaultWeaponModel();
		myWeaponDimension = new Dimension(DEFAULT_WEAPON_WIDTH, DEFAULT_WEAPON_HEIGHT);
	}
	
	public Velocity getProjectileVelocity() {
		return myWeaponModel.getProjectileModel().getInitalVelocity();
	}
	
	public void setWeaponModel(WeaponModel weaponModel) {
		myWeaponModel = weaponModel;
	}
	
	public WeaponModel getWeaponModel() {
		return myWeaponModel;
	}
	
	public void setWeaponDimension(Dimension weaponDimension) {
		myWeaponDimension = weaponDimension;
	}
	
	public Dimension getWeaponDimension() {
		return myWeaponDimension;
	}
	
	@Override
	public void affect(IUpgrader upgrader) {
		upgrader.obtainWeapon(myWeaponModel, myWeaponDimension);
	}

}
