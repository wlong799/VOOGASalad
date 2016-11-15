package game_engine;

import java.util.ArrayList;

import game_object.character.AbstractCharacter;
import voogasalad_overwatch.Sprite;

public class Gravity {
	private ArrayList<Sprite> myGrounds;
	private static final double G = 10;
	private double myGravity;

	public Gravity() {
		new Gravity(G);
	}

	public Gravity(double g) {
		myGravity = g;
		myGrounds = new ArrayList<Sprite>();
	}

	public void calculateNewVerticalPosition(AbstractCharacter character, double elapsedTime) {
		
		double y = character.getPosition().getY();
		double vy = character.getVelocity().getYVelocity();
		double newy = y + elapsedTime * vy;
		double newvy = vy + elapsedTime * myGravity;
		
		boolean touched = checkTouchedGround(character, newy);
		updateNewPositionAndVelocity(newy, newvy, touched, character); //touched any ground or concrete blocks

	}

	public boolean checkTouchedGround(AbstractCharacter character, double newy) {
		//TODO: loop through all the (can-stand-on) blocks can check if they collide with the top border of the hit box
		return false;
	}
	
	private void updateNewPositionAndVelocity(double newy, double newvy, boolean touched, AbstractCharacter character) {
		if (touched) {
			character.getVelocity().setYVelocity(0);
			return;
		}
		
		//new position = current position + dy
		character.getPosition().setY(newy);
		
		//new velocity = current velocity +dvy
		character.getVelocity().setYVelocity(newvy);
	}
}
