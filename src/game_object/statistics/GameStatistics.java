package game_object.statistics;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_object.character.Hero;
import game_object.core.Game;
import game_object.core.Position;

/**
 * A GameStatistics class providing all available fields for HUD
 * @author Jay
 */
public class GameStatistics {

	private static DecimalFormat staticDF2 = new DecimalFormat(".##");
	private Game myGame;
	private Map<StatisticsField, Boolean> myValidFieldsMap;
	private Map<Hero, List<String>> myHeroStats;
	
	public GameStatistics(Game game) {
		myGame = game;
		myValidFieldsMap = new HashMap<>();
		
		enableAll();
	}
	
	private Hero getHeroWithIndex(int index) {
		return myGame.getCurrentLevel().getHeros().get(index);
	}
	
	private void applyToAll(boolean valid) {
		for (StatisticsField field : StatisticsField.values()) {
			setValid(field, valid);
		}
	}
	
	public void enableAll() {
		applyToAll(true);
	}
	
	public void disableAll() {
		applyToAll(false);
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
	
	public Position getPositionOfHero(int index){
		return isValid(StatisticsField.POSITION)
				? getHeroWithIndex(index).getPosition()
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
	
	public String getScoreOfHero(int index) {
		return isValid(StatisticsField.SCORE)
			? "" + staticDF2.format(getHeroWithIndex(index).getTotalScore())
			: null;
	}
	
}
