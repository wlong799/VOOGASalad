package game_engine.transition;

import game_object.framework.Game;

public abstract class AbstractTransitionManager implements ITransitionManager{

	Game myGame;
	
	public AbstractTransitionManager(Game game) {
		myGame = game;
	}
	
	public void readWinStatus(WinStatus ws) {
		if (ws==WinStatus.WON) levelWon();
		else if (ws==WinStatus.LOST) levelLost();
		else if (ws==WinStatus.GOON) levelGoOn();
	}
	
	public abstract void levelWon();

	public abstract void levelLost();

	public abstract void levelGoOn();

}
