package game_engine;

import java.util.List;

import game_object.block.AbstractBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.framework.Game;
import game_object.level.Level;
import game_object.level.TransitionMenu;

public class GameEngine implements IGameEngine{
	private boolean runFlag;
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
	}
	
	@Override
	public void loop() {
		init();
		while (runFlag) {
			endCheck();
			update();
			draw();
		}
		shutdown();
	}
	
	@Override
	public void init() {
		//drawMenu();
	}

	@Override
	public void endCheck() {
		
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

}
