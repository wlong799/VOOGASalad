package game_player;

import game_object.core.Game;
import game_player_menu.GamePlayMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class GamePlayManager extends Application implements ISceneManager {
	private Stage myStage;
	//private Scene myCurrentScene;
	//private GamePlayer myGamePlayer;
	//private XStream mySerializer = new XStream(new DomDriver());
	//private Game myCurrentGame;
	
	public void start(Stage s) {
		myStage = s;
		new GamePlayMenu(s, this);
	}

	@Override
	public void playGame(Game game) {
		new GamePlayer(myStage, game);
	}
	
}
