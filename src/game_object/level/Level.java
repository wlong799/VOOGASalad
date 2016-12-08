package game_object.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game_engine.physics.PhysicsParameters;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.Event;
import game_object.background.Background;
import game_object.block.Block;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.constants.DefaultConstants;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Game;
import game_object.core.ISprite;
import game_object.powerup.IPowerUp;
import game_object.visualization.ILevelVisualization;
import game_object.visualization.ISpriteVisualization;
import game_object.weapon.Projectile;
import goal.IGoal;

/**
 * A class representing a level.
 * @author Jay
 */
public class Level implements ILevelVisualization {

	private final String myId;
	private final Game myParentGame;
	private Dimension myLevelDimension;
	private Level myNextLevel;
	private TransitionMenu myNextMenu;
	private Background myBackground;
	private PhysicsParameters myPhysicsParameters;
	private List<IGoal> myGoals;
	private List<Hero> myHeros;
	private List<Enemy> myEnemies;
	private List<Block> myStaticBlocks;
	private List<ActionTrigger> myTriggers;
	private List<Projectile> myProjectiles;
	private List<IPowerUp> myPowerUps;
	
	public Level(Game parentGame, String id) {
		myParentGame = parentGame;
		myId = id;
		myHeros = new ArrayList<>();
		myEnemies = new ArrayList<>();
		myStaticBlocks = new ArrayList<>();
		myTriggers = new ArrayList<>();
		myProjectiles = new ArrayList<>();
		myPowerUps = new ArrayList<>();
		myLevelDimension = new Dimension(DefaultConstants.LEVEL_WIDTH, 
				DefaultConstants.LEVEL_HEIGHT);
		myPhysicsParameters = new PhysicsParameters();
		myGoals = new ArrayList<>();
		myBackground = new Background();
		
	}
	
	public String getId() {
		return myId;
	}
	
	public Game getParentGame() {
		return myParentGame;
	}
	
	public List<ISprite> getAllSprites() {
		List<ISprite> spriteList = new ArrayList<>();
		for (List<? extends ISprite> list : getAllLists()) {
			spriteList.addAll(list);
		}
		return spriteList;
	}
	
	/* Level Dimensions */
	public Dimension getLevelDimension() {
		return myLevelDimension;
	}
	/* ---Level Dimensions END ---*/
	
	/* Engine Settings */
	public PhysicsParameters getPhysicsParameters() {
		return myPhysicsParameters;
	}
	
	public void setPhysicsParameters(PhysicsParameters physicsParameters) {
		myPhysicsParameters = physicsParameters;
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
		} else if (sprite instanceof Block) {
			myStaticBlocks.add((Block)sprite);
		} else if(sprite instanceof IPowerUp) {
			myPowerUps.add((IPowerUp)sprite);
		}
	}
	
	public void removeSprite(ISprite sprite) {
		for (List<? extends ISprite> list : getAllLists()) {
			list.remove(sprite);
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

	/**
	 * Use with caution.
	 * Essentially make this level's myHeros list share the same reference with another level.
	 * This will cause the original list to be garbage-collected.
	 * @param level another level
	 */
	public void replaceAllHerosAndTriggersWithLevel(Level level) {
		myHeros = level.getHeros();
		myTriggers = level.getAllTriggers();
	}
	
	public List<Hero> getHeros() {
		return myHeros;
	}

	public List<Enemy> getEnemies() {
		return myEnemies;
	}

	public List<Block> getStaticBlocks() {
		return myStaticBlocks;
	}
	
	
	public List<Projectile> getProjectiles() {
		return myProjectiles;
	}
	
	public List<IPowerUp> getPowerUps() {
		return myPowerUps;
	}
	
	/* ---Accessors for background, characters and blocks END--- */

	/* Events and Triggers */
	public List<ActionTrigger> getAllTriggers() {
		return myTriggers;
	}
	
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
	/* ---Events and Triggers END--- */

	/* Goals */
	public List<IGoal> getAllGoals() {
		return myGoals;
	}
	/* ---Goals END--- */
	
	/* ILevelVisualization Implementations */
	@Override
	public void init() {
		AbstractSprite.setStaticPivotDimension(getParentGame().getScreenSize());
	}
	
	@Override
	public List<ISpriteVisualization> getAllSpriteVisualizations() {
		if (AbstractSprite.isGarbageCollectNeeded()) {
			cleanup();
		}
		List<ISprite> allSprites = getAllSprites();
		allSprites.sort((s1, s2) ->
			s1.getPosition().getZ() > s2.getPosition().getZ() ? -1 : 1
		);
		List<ISpriteVisualization> visuals = new ArrayList<>();
		for (ISprite sprite : allSprites) {
			if (sprite.isValid()) {
				visuals.add(sprite);
			}
		}
		return visuals;
	}
	/* ---ILevelVisualization Implementations END--- */
	
	/* private */	
	private Set<List<? extends ISprite>> getAllLists() {
		Set<List<? extends ISprite>> listSet = new HashSet<>();
		listSet.add(myHeros);
		listSet.add(myEnemies);
		listSet.add(myStaticBlocks);
		listSet.add(myPowerUps);
		listSet.add(myProjectiles);
		return listSet;
	}
	
	private void cleanup() {
		for (List<? extends ISprite> list : getAllLists()) {
			for (ISprite sprite : list) {
				if (!sprite.isValid()) {
					list.remove(sprite);
				}
			}
		}
	}
	/* private END--- */
}
