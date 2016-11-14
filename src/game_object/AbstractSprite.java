package game_object;

import voogasalad_overwatch.Collision;

import java.util.*;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public class AbstractSprite implements ISprite {

	Position myPosition;
	ArrayList<String> myImgPaths;
	double myHP;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collided(ISprite otherSprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHP(double hp) {
		myHP = hp;
	}
	
	@Override
	public double getHP() {
		return myHP;
	}

	@Override
	public void setCollisionProperty(Collision coll) {
		// TODO Auto-generated method stub
		
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
