package game_engine;

import game_object.level.AbstractLevel;
import voogasalad_overwatch.Game;

public class GameEngine implements IGameEngine{
	private boolean runFlag;
	private Game myGame;
	private AbstractLevel myCurrentLevel;
	
	public GameEngine(Game game) {
		myGame = game;
		runFlag = true;
	}
	
	@Override
	public void loop() {
		while (runFlag) {
			endCheck();
			update();
			draw();
		}
		shutdown();
	}
	
	@Override
	public void init() {
		
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
