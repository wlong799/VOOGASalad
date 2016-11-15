package game_object;

import java.util.ArrayList;
import java.util.List;

import game_object.character.Enemy;
import game_object.character.Hero;

/**
 * A class representing a level.
 * @author Jay
 */
public class Level {

	Level myNextLevel;
	TransitionMenu myNextMenu;
	List<Hero> myHeros;
	List<Enemy> myEnemies;
	//TODO: List<Block> myBlocks;
	
	public Level() {
		myHeros = new ArrayList<>();
		myEnemies = new ArrayList<>();
	}
	
	/* Transitions. Note if getNextLevel() returns a non-null value,  getNextMenu() will be ignored. */
	public void setNextLevel(Level nextLevel) {
		myNextLevel = nextLevel;
	}
	
	public Level getNextLevel() {
		return myNextLevel;
	}
	
	public void setNextMenu(TransitionMenu nextMenu) {
		myNextMenu = nextMenu;
	}
	
	public TransitionMenu getNextMenu() {
		return myNextMenu;
	}
	
	/* Add/Remove specific sprites */
	public void addHero(Hero hero) {
		myHeros.add(hero);
	}
	
	public void removeHero(Hero hero) {
		myHeros.remove(hero);
	}
	
	public void addEnemy(Enemy enemy) {
		myEnemies.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		myEnemies.remove(enemy);
	}
	
}
