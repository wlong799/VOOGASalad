package game_player;

import java.util.List;

import javafx.scene.input.KeyCode;

public interface IGamePlayer {

	IHighScoreStore getHighScoreStore();
	
	IGamePreferenceManager getGamePreferenceManager();
	
	void setGame(String gameName); // load game properly
	
	void start(); // also replay
	
	void save();

	/**
	 * include each game's name, image, and description
	 */
	//List<GameInfo> getAllGameInfo();
	
	//Initial test method used in setting up an initial menu for game play 
	//view
	List<FakeGameInfo> getAllGameInfo();
	
	/**
	 * on a frame by frame basis
	 */
	void update(List<KeyCode> currentlyPressedKeys);

}
