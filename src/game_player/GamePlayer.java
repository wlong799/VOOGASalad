package game_player;

import game_object.core.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author samuelcurtis, billyu
 *This class is the front end view for game engine. It takes in a game object, and then repeatedly 
 *calls methods from the IGameEngine interface at a specific framerate in order to receive all of the objects
 *it needs to render onto the screen. It is  also responsible for detecting all user key input and 
 *sending that to the game engine.
 */

public class GamePlayer implements IGamePlayer {
	
	private Stage myStage;
	private Scene myScene;
	private GameRunner myRunner;
	private Game myCurrentGame;

	public GamePlayer(Stage s, Game game) {
		myStage = s;
		myScene = new Scene(new Group(), game.getScreenSize().getWidth(), game.getScreenSize().getHeight());
		myRunner = new GameRunner(myScene, game, level->{});
		myScene.setRoot(myRunner.getRunningView().getViews());
		myStage.setScene(myScene);
		myStage.show();
	}

	//This will eventually already take a game object in from a serializer
	@Override
	public void setGame(String gameName) {
		myCurrentGame = new Game();
		//myGameEngine = new GameEngine_Game(myCurrentGame);
	}

	@Override
	public void start() {
	/*	final long startNanoTime = System.nanoTime();
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
				myGameEngine.update(t);
				renderSprites();
			}
		}.start();
		myStage.show();*/
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public IHighScoreStore getHighScoreStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGamePreferenceManager getGamePreferenceManager() {
		// TODO Auto-generated method stub
		return null;
	}

	/*	@Override
	public List<GameInfo> getAllGameInfo() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
