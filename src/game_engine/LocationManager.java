package game_engine;

import game_object.Position;
import game_object.Velocity;
import game_object.character.AbstractCharacter;

public class LocationManager {
	Gravity myGravity;
	
	public LocationManager() {
		myGravity = new Gravity();
	}
	
	public double calculateNewVerticalPosition(AbstractCharacter character, double elapsedTime) {
		
		double y = character.getPosition().getY();
		double vy = character.getVelocity().getYVelocity();
		double newy = y + elapsedTime * vy;
		double newvy = vy + elapsedTime * myGravity.getGravity();
		
		boolean touched = checkTouchedGround(character, newy);
		updateNewPositionAndVelocity(newy, newvy, touched, character); //touched any ground or concrete blocks
		return 0.0;
	}

	public double calculateNewVerticalVelocity(AbstractCharacter character, double elapsedTime) {
		return 0.0;
	
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
