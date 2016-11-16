package game_engine;

import java.util.List;

import game_engine.physics.PhysicsEngine;
import game_object.block.AbstractBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.framework.Game;
import game_object.level.Level;
import game_object.level.TransitionMenu;

/**
 * 
 * @author Charlie Wang
 */
public class GameEngine implements IGameEngine{
	private boolean runFlag;
	private PhysicsEngine myPhysicsEngine;
	private Game myGame;
	private Level myCurrentLevel;
	private TransitionMenu myFirstSceneAsMenu;
	private Level myFirstSceneAsLevel;
	private List<Hero> myHeros;
	private List<Enemy> myEnemies;
	private List<AbstractBlock> myBlocks;
	
	public GameEngine(Game game) {
		myGame = game;
		runFlag = true;
		myFirstSceneAsMenu = game.getFirstSceneAsMenu();
		myFirstSceneAsLevel = game.getFirstSceneAsLevel();
		myCurrentLevel = myFirstSceneAsLevel;
		myPhysicsEngine = new PhysicsEngine();
	}
	
	@Override
	public void loop() {
		menu();
		init();
		while (runFlag) {
			endCheck();
			update();
			draw();
		}
		shutdown();
	}
	
	private void menu() {
		
	}
	
	@Override
	public void init() {
		setElements(myCurrentLevel);
		//drawMenu();
	}

	@Override
	public void endCheck() {
		
	}
	
	public void transitLevel() {
		myCurrentLevel = myCurrentLevel.getNextLevel();
		setElements(myCurrentLevel);
	}
	
	@Override
	public void shutdown() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw() {
		
	}

	public void setElements(Level level) {
		myHeros = level.getHeros();
		myEnemies = level.getEnemies();
		myBlocks = level.getBlocks();
	}
}
