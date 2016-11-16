package game_engine;

import game_object.Game;
import game_object.Level;
import game_object.TransitionMenu;

public class GameEngine implements IGameEngine{
	private boolean runFlag;
	private Game myGame;
	private Level myCurrentLevel;
	private TransitionMenu myFirstSceneAsMenu;
	private Level myFirstSceneAsLevel;
	
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
