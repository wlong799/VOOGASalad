package game_engine.physics;

import java.util.ArrayList;
import java.util.List;

import game_object.core.AbstractSprite;

/**
 * Class that represent the basic behaviors of gravity
 * 
 * @author Charlie Wang
 */
public class Gravity {
	private ArrayList<AbstractSprite> myGrounds;
	private static final double G = 10;
	private double myGravity;

	public Gravity() {
		new Gravity(G);
	}

	public Gravity(double g) {
		myGravity = g;
		myGrounds = new ArrayList<AbstractSprite>();
	}

	public double getGravity() {
		return myGravity;
	}
	
	public List<AbstractSprite> getGrounds() {
		return myGrounds;
	}
}
