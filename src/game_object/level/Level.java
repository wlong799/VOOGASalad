package game_object.level;

import java.util.ArrayList;
import java.util.List;

import game_engine.physics.PhysicsParameters;
import game_object.acting.ActionName;
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
import goal.IGoal;

/**
 * A class representing a level.
 * @author Jay
 */
public class Level implements ILevelVisualization {

	private Dimension myLevelDimension;
	private Level myNextLevel;
	private TransitionMenu myNextMenu;
	private Background myBackground;
	private PhysicsParameters myPhysicsParameters;
	private List<IGoal> myGoals;
	private List<Hero> myHeros;
	private List<Enemy> myEnemies;
	private List<StaticBlock> myStaticBlocks;
	private List<ActionTrigger> myTriggers;
	
	public Level() {
		myHeros = new ArrayList<>();
		myEnemies = new ArrayList<>();
		myStaticBlocks = new ArrayList<>();
		myTriggers = new ArrayList<>();
		myLevelDimension = new Dimension(0, 0);
		myPhysicsParameters = new PhysicsParameters();
		myGoals = new ArrayList<>();
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
	
	/* Engine Settings */
	public PhysicsParameters getPhysicsParameters() {
		return myPhysicsParameters;
	}
	/* --- Engine Settings END --- */
	
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
	public void addSprite(ISprite sprite) {
		if (sprite instanceof Hero) {
			myHeros.add((Hero)sprite);
		} else if (sprite instanceof Enemy) {
			myEnemies.add((Enemy)sprite);
		} else if (sprite instanceof StaticBlock) {
			myStaticBlocks.add((StaticBlock)sprite);
		}
	}
	
	public void removeSprite(ISprite sprite) {
		if (sprite instanceof Hero) {
			myHeros.remove((Hero)sprite);
		} else if (sprite instanceof Enemy) {
			myEnemies.remove((Enemy)sprite);
		} else if (sprite instanceof StaticBlock) {
			myStaticBlocks.remove((StaticBlock)sprite);
		}
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
	public List<ActionTrigger> getAllTriggers() {
		return myTriggers;
	}
//	
//	public void addTrigger(ActionTrigger trigger) {
//		myTriggers.add(trigger);
//	}
//	
//	public void removeTrigger(ActionTrigger trigger) {
//		myTriggers.remove(trigger);
//	}
	
	public ActionTrigger getTriggerWithSpriteAndAction(
		ISprite sprite,
		ActionName actionName
	) {
		for (ActionTrigger trigger : myTriggers) {
			if (
				trigger.getSprite().equals(sprite) &&
				trigger.getActionName().equals(actionName)
			) {
				return trigger;
			}
		}
		return null;
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

	public List<IGoal> getAllGoals() {
		return myGoals;
	}
	
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
