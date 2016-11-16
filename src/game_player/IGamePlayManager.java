package game_player;

import java.util.List;

import game_object.Game;

public interface IGamePlayManager {

	public List<Game> getAvailableGames();
	
	public Game chooseGame();
	
}
