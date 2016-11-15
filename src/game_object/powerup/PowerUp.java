package game_object.powerup;

import game_object.character.Upgrader;
import java.util.*;

public abstract class PowerUp {
	
	private double myX, myY;
	private ArrayList<String> myImgPaths;

	public PowerUp(double x, double y, ArrayList<String> imgPaths) {
		myX = x;
		myY = y;
		myImgPaths = imgPaths;
	}
	
	public abstract void affect(Upgrader u);

	public double getX() {
		return myX;
	}

	public void setX(double x) {
		myX = x;
	}

	public double getY() {
		return myY;
	}

	public void setY(double y) {
		myY = y;
	}

	public ArrayList<String> getImgPaths() {
		return myImgPaths;
	}

	public void setImgPaths(ArrayList<String> imgPaths) {
		myImgPaths = imgPaths;
	}

	
}
