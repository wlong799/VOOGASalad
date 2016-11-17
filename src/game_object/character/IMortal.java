package game_object.character;

/**
 * A mortal being that has HP settings and will die.
 * @author Jay
 */
public interface IMortal {
	
	void setMaxHP(int maxHP);
	
	double getMaxHP();
	
	void setCurrentHP(double currentHP);
	
	double getCurrentHP();
	
	void setDead(boolean dead);
	
	boolean getDead();
	
}
