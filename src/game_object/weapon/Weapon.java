package game_object.weapon;

import java.util.List;

import game_object.character.ICharacter;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;

public class Weapon extends AbstractSprite {

	private static final double Z_POS_OFFSET_TO_OWNER = 0.01f;
	WeaponModel myModel;
	ICharacter myOwner;
	
	public Weapon(Position position, Dimension dimension, List<String> imagePaths, WeaponModel model) {
		super(position, dimension, imagePaths);
		myModel = model;
	}

	public WeaponModel getModel() {
		return myModel;
	}

	public void setModel(WeaponModel model) {
		myModel = model;
	}
	
	public void setOwner(ICharacter owner) {
		myOwner = owner;
	}
	
	@Override
	public Position getPosition() {
		if (myOwner == null) {
			return myPosition;
		}
		myFacingLeft = myOwner.isFacingLeft();
		return new Position(
			myOwner.getWeaponX(),
			myOwner.getWeaponY(),
			myOwner.getPosition().getZ() + Z_POS_OFFSET_TO_OWNER
		);
	}
}
