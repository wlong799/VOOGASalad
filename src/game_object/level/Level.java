package game_object.level;

import java.util.ArrayList;
import java.util.List;

import game_object.acting.ActionTrigger;
import game_object.acting.Event;
import game_object.background.Background;
import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.visualization.ILevelVisualization;
import game_object.visualization.ISpriteVisualization;

/**
 * A class representing a level.
 * @author Jay
 */
public class Level implements ILevelVisualization {

	private Dimension myLevelDimension;
	private Level myNextLevel;
	private TransitionMenu myNextMenu;
	private Background myBackground;
	private List<Hero> myHeros;
	private List<Enemy> myEnemies;
	private List<StaticBlock> myStaticBlocks;
	private List<ActionTrigger> myTriggers;
	
	public Level() {
		myHeros = new ArrayList<>();
		myEnemies = new ArrayList<>();
		myStaticBlocks = new ArrayList<>();
		myTriggers = new ArrayList<>();
	}
	
	public List<ISprite> getAllSprites() {
		List<ISprite> spriteList = new ArrayList<>();
		spriteList.addAll(myHeros);
		spriteList.addAll(myEnemies);
		spriteList.addAll(myStaticBlocks);
		return spriteList;
	}
	
	/* Level Dimensions */
	public void setLevelDimension(Dimension levelDimension) {
		myLevelDimension = levelDimension;
	}
	
	public Dimension getLevelDimension() {
		return myLevelDimension;
	}
	/* ---Level Dimensions END ---*/
	
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
	/* ---Transitions END--- */
	
	/* Add/Remove specific sprites */
	public void removeSprite(ISprite sprite) {
		if (sprite instanceof Hero) {
			removeHero((Hero)sprite);
		}
		else if (sprite instanceof Enemy) {
			removeEnemy((Enemy)sprite);
		}
		else if (sprite instanceof StaticBlock) {
			removeStaticBlock((StaticBlock)sprite);
		}
	}
	
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

	public void addStaticBlock(StaticBlock staticBlock) {
		myStaticBlocks.add(staticBlock);
	}
	
	public void removeStaticBlock(StaticBlock staticBlock) {
		myStaticBlocks.remove(staticBlock);
	}
	/* ---Add/Remove specific sprites END--- */
	
	
	/* Accessors for background, characters and blocks */
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

	public List<StaticBlock> getStaticBlocks() {
		return myStaticBlocks;
	}

	public void setStaticBlocks(List<StaticBlock> blocks) {
		myStaticBlocks = blocks;
	}
	/* ---Accessors for background, characters and blocks END--- */

	
	/* Events and Triggers */
	public void addTrigger(ActionTrigger trigger) {
		myTriggers.add(trigger);
	}
	
	public void removeTrigger(ActionTrigger trigger) {
		myTriggers.remove(trigger);
	}
	
	public List<ActionTrigger> getTriggersWithEvent(Event event) {
		List<ActionTrigger> triggersWithEvent = new ArrayList<>();
		for (ActionTrigger trigger : myTriggers) {
			if (trigger.getEvent().equals(event)) {
				triggersWithEvent.add(trigger);
			}
		}
		return triggersWithEvent;
	}
	/* ---Events and Triggers--- */

	
	/* ILevelVisualization Implementations */
	List<ISpriteVisualization> mySpriteVisualizations;
	
	@Override
	public void init() {
		mySpriteVisualizations = new ArrayList<>();
		List<ISprite> allSprites = getAllSprites();
		allSprites.sort((s1, s2) ->
			s1.getPosition().getZ() > s2.getPosition().getZ() ? 1 : -1
		);
		mySpriteVisualizations.addAll(allSprites);
	}

	@Override
	public List<ISpriteVisualization> getAllSpriteVisualizations() {
		return mySpriteVisualizations;
	}
	/* ---ILevelVisualization Implementations END--- */
	
}
