package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_object.AbstractSprite;

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
