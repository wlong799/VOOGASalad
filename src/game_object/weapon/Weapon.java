package game_object.weapon;

import java.util.List;
import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;

public class Weapon extends AbstractSprite {

	WeaponModel myModel;
	
	public Weapon(Position position, Dimension dimension, List<String> imagePaths, WeaponModel model) {
		super(position, dimension, imagePaths);
		myModel = model;
	}

	@Override
	public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection) {
		
	}

	public WeaponModel getModel() {
		return myModel;
	}

	public void setModel(WeaponModel model) {
		myModel = model;
	}
	
}
