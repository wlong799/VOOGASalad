package game_object.statistics;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import game_object.character.Hero;
import game_object.core.Game;

/**
 * A GameStatistics class providing all available fields for HUD
 * @author Jay
 */
public class GameStatistics {

	private static DecimalFormat staticDF2 = new DecimalFormat(".##");
	private Game myGame;
	private Map<StatisticsField, Boolean> myValidFieldsMap;
	
	public GameStatistics(Game game) {
		myGame = game;
		myValidFieldsMap = new HashMap<>();
	}
	
	private Hero getHeroWithIndex(int index) {
		return myGame.getCurrentLevel().getHeros().get(index);
	}
	
	public void setValid(StatisticsField field, boolean valid) {
		myValidFieldsMap.put(field, valid);
	}
	
	public boolean isValid(StatisticsField field) {
		return myValidFieldsMap.get(field);
	}
	
	/* Field Getters */
	public String getFPS() {
		return isValid(StatisticsField.FPS)
			? "" + myGame.getFPS()
			: null;
	}
	
	public String getLevelId() {
		return isValid(StatisticsField.LEVEL_ID)
			? myGame.getCurrentLevel().getId()
			: null;
	}
	
	public String getHealthOfHero(int index) {
		return isValid(StatisticsField.HEALTH)
			? "" + (int)getHeroWithIndex(index).getCurrentHP()
			: null;
	}
	
	public String getXPosOfHero(int index) {
		return isValid(StatisticsField.POSITION)
			? "" + staticDF2.format(getHeroWithIndex(index).getPosition().getX())
			: null;
	}
	
	public String getYPosOfHero(int index) {
		return isValid(StatisticsField.POSITION)
			? "" + staticDF2.format(getHeroWithIndex(index).getPosition().getY())
			: null;
	}
	
}
