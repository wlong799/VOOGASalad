package game_object.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import game_engine.collision.Boundary;
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
import game_object.core.Position;
import game_object.core.Velocity;
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
	private Boundary myBoundary;
	private Dimension myDimension;
	private Level myNextLevel;
	private TransitionMenu myNextMenu;
	private Background myBackground;
	private PhysicsParameters myPhysicsParameters;
	private List<IGoal> myGoals;
	private List<Hero> myHeros;
	private List<Enemy> myEnemies;
	private List<Block> myBlocks;
	private List<ActionTrigger> myTriggers;
	private List<Projectile> myProjectiles;
	private List<IPowerUp> myPowerUps;
	private SpriteScavenger mySpriteScavenger;
	
	public Level(Game parentGame, String id) {
		myParentGame = parentGame;
		myId = id;
		myHeros = new ArrayList<>();
		myEnemies = new ArrayList<>();
		myBlocks = new ArrayList<>();
		myTriggers = new ArrayList<>();
		myProjectiles = new ArrayList<>();
		myPowerUps = new ArrayList<>();
		myBoundary = new Boundary(new Position(0,0), new Dimension(DefaultConstants.LEVEL_WIDTH, 
		                                                           DefaultConstants.LEVEL_HEIGHT));
		myDimension = new Dimension(DefaultConstants.LEVEL_WIDTH, 
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
		cleanup();
		List<ISprite> spriteList = new ArrayList<>();
		spriteList.addAll(myHeros);
		spriteList.addAll(myEnemies);
		spriteList.addAll(myBlocks);
		spriteList.addAll(myPowerUps);
		spriteList.addAll(getRuntimeSprites());
		
		List<ISprite> spriteListWithChildren = new ArrayList<>();
		for (ISprite sprite : spriteList) {
			spriteListWithChildren.addAll(sprite.getChildSprites().getSprites());
		}
		spriteListWithChildren.addAll(spriteList);
		return spriteListWithChildren;
	}
	
	/* Level Dimensions */
	public Dimension getDimension(){
	    return myDimension;
	}
	
	/* Level Boundary */
	public Boundary getBoundary() {
		return myBoundary;
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
	    if(sprite.getPosition().getX() > this.myLevelDimension.getWidth()){
	        myLevelDimension.setWidth(sprite.getPosition().getX());
	    }
	    if(sprite.getPosition().getY() > myLevelDimension.getHeight()){
	        myLevelDimension.setHeight(sprite.getPosition().getY());
	    }
		if (sprite instanceof Hero) {
			myHeros.add((Hero)sprite);
		} else if (sprite instanceof Enemy) {
			myEnemies.add((Enemy)sprite);
		} else if (sprite instanceof Block) {
			myBlocks.add((Block)sprite);
		} else if (sprite instanceof IPowerUp) {
			myPowerUps.add((IPowerUp)sprite);
		} else if (sprite instanceof Projectile) {
			myProjectiles.add((Projectile)sprite);
		}
	}
	
	public void removeSprite(ISprite sprite) {
		if (sprite instanceof Hero) {
			myHeros.remove(sprite);
		} else if (sprite instanceof Enemy) {
			myEnemies.remove(sprite);
		} else if (sprite instanceof Block) {
			myBlocks.remove(sprite);
		} else if(sprite instanceof IPowerUp) {
			myPowerUps.remove(sprite);
		} else if (sprite instanceof Projectile) {
			myProjectiles.remove(sprite);
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
		return Collections.unmodifiableList(myHeros);
	}

	public List<Enemy> getEnemies() {
		return Collections.unmodifiableList(myEnemies);
	}

	public List<Block> getStaticBlocks() {
		return Collections.unmodifiableList(myBlocks);
	}
	
	
	public List<Projectile> getProjectiles() {
		return Collections.unmodifiableList(myProjectiles);
	}
	
	public List<IPowerUp> getPowerUps() {
		return Collections.unmodifiableList(myPowerUps);
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
	private List<ISpriteVisualization> mySpriteVisuals = new ArrayList<>();
	
	@Override
	public void init() {
		if (myHeros.size() == 0) {
			myHeros.add(Hero.generateDefaultHero());
		}
		AbstractSprite.setStaticPivotDimension(getParentGame().getScreenSize());
		mySpriteScavenger = AbstractSprite.getSpriteScavenger();
		mySpriteScavenger.setBorderDimension(myParentGame.getScreenSize());
	}

	@Override
	public void update() {
		List<ISprite> allSprites = getAllSprites();
		allSprites.sort((s1, s2) ->
			s1.getPosition().getZ() > s2.getPosition().getZ() ? 1 : -1
		);
		for (ISprite sprite : allSprites) {
			sprite.setPreviousPosition(Position.getCopiedInstance(sprite.getPosition()));
			sprite.setPreviousVelocity(Velocity.getCopiedInstance(sprite.getVelocity()));
		}
		myBoundary.getPosition().setX(myHeros.get(0).getScrollOffset());
		mySpriteVisuals.clear();
		mySpriteVisuals.addAll(allSprites);
	}
	
	@Override
	public List<ISpriteVisualization> getAllSpriteVisualizations() {
		return mySpriteVisuals;
	}
	
	/* ---ILevelVisualization Implementations END--- */
	
	/* private */
	private List<ISprite> getRuntimeSprites() {
		List<ISprite> runtimeSprites = new ArrayList<>();
		runtimeSprites.addAll(myProjectiles);
		return runtimeSprites;
	}
	
	private void cleanup() {
		//I intentionally made this verbose just for my own sanity.
		mySpriteScavenger.scavengeList(myEnemies);
		mySpriteScavenger.scavengeList(myProjectiles);
		mySpriteScavenger.scavengeList(myPowerUps);
		mySpriteScavenger.scavengeList(myBlocks);
	}
	/* private END--- */
}
