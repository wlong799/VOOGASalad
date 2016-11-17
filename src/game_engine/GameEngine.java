package game_engine;

import java.util.List;

import game_engine.physics.AbstractPhysicsEngine;
import game_engine.physics.PhysicsEngine;
import game_engine.transition.AbstractTransitionManager;
import game_engine.transition.TransitionManager;
import game_engine.transition.WinStatus;
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
public class GameEngine implements IGameEngine {
	private static final int FPS = 30;
	private boolean runFlag;
	private AbstractPhysicsEngine myPhysicsEngine;
	private AbstractTransitionManager myTransitionManager;
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
		myPhysicsEngine = new PhysicsEngine(FPS);
		myTransitionManager = new TransitionManager(game, myCurrentLevel);
	}

	@Override
	public void loop() {
		init();
		while (runFlag) {
			long time = System.currentTimeMillis();
			
			endCheck();
			update();
			draw();
			
			time = (1000 / FPS) - (System.currentTimeMillis() - time);
			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
					//
					e.printStackTrace();
				}
			}
		}
		shutdown();
	}

	private void menu() {
		// TODO: pass all the objects in the menu level to the game player team
		// return: flag that its good to go (first level) (tentative)
	}

	@Override
	public void init() {
		setElements(myCurrentLevel);
		// TODO: pass to game play team to render
	}

	@Override
	public void endCheck() {
		WinStatus ws = checkWin();
		if (ws != WinStatus.GOON) {
			myCurrentLevel = myTransitionManager.readWinStatus(ws);
			if (myCurrentLevel == null) {
				shutdown();
			}
			init();
		}
	}

	@Override
	public void shutdown() {
		// TODO: go to game over screen/close stage
	}

	@Override
	public void update() {
		// myPhysicsEngine.updateBlocks(myCurrentLevel);
		// myPhysicsEngine.updateEnemies(myCurrentLevel);
		// myPhysicsEngine.updateHeroes(myCurrentLevel);
	}

	@Override
	public void draw() {

	}

	private WinStatus checkWin() {
		// TODO: check whether a Goal has been reached
		WinStatus ws = null;
		return ws;
	}

	public void setElements(Level level) {
		myHeros = level.getHeros();
		myEnemies = level.getEnemies();
		myBlocks = level.getBlocks();
	}

	public void setPhysicsEngine(AbstractPhysicsEngine physicsEngine) {
		myPhysicsEngine = physicsEngine;
	}

	public void setTransitionManager(AbstractTransitionManager transitionManager) {
		myTransitionManager = transitionManager;
	}
}
