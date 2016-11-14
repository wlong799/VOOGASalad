package game_object;

import voogasalad_overwatch.Collision;

import java.util.*;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public abstract class AbstractSprite implements ISprite {

	protected Position myPosition;
	protected ArrayList<String> myImgPaths;
	protected boolean myDead;
	protected Collision myCollision;
	
	@Override
	public void setPosition(Position pos) {
		myPosition = pos;
	}

	@Override
	public Position getPosition() {
		return myPosition;
	}
	
	@Override
	public void setDead(boolean dead) {
		myDead = dead;
	}

	@Override
	public boolean isDead() {
		return myDead;
	}

	@Override
	public void setCollisionProperty(Collision coll) {
		myCollision = coll;
	}

	@Override
	public ArrayList<String> getImagePaths() {
		return myImgPaths;
	}

	@Override
	public void setImagePaths(ArrayList<String> imgPaths) {
		myImgPaths = imgPaths;
	}

}
