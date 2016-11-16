package game_object;

import java.util.ArrayList;
import java.util.List;

import game_object.background.Background;
import game_object.block.AbstractBlock;
import game_object.character.Enemy;
import game_object.character.Hero;

/**
 * A class representing a level.
 * @author Jay
 */
public class Level {

	private Level myNextLevel;
	private TransitionMenu myNextMenu;
	private Background myBackground;
	private List<Hero> myHeros;
	private List<Enemy> myEnemies;
	private List<AbstractBlock> myBlocks;
	
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

	public Background getBackground() {
		return myBackground;
	}

	public void setBackground(Background background) {
		myBackground = background;
	}

	public List<Hero> getHeros() {
		return myHeros;
	}

	public void setHeros(List<Hero> heros) {
		myHeros = heros;
	}

	public List<Enemy> getEnemies() {
		return myEnemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		myEnemies = enemies;
	}

	public List<AbstractBlock> getBlocks() {
		return myBlocks;
	}

	public void setBlocks(List<AbstractBlock> blocks) {
		myBlocks = blocks;
	}
	
}
