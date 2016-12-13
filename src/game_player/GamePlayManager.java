package game_player;

import game_object.core.Game;
import game_player_menu.GamePlayMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class GamePlayManager extends Application implements ISceneManager {
	private Stage myStage;
	private GamePlayMenu myMainMenu;
	//private Scene myCurrentScene;
	//private GamePlayer myGamePlayer;
	//private XStream mySerializer = new XStream(new DomDriver());
	//private Game myCurrentGame;
	
	public void start(Stage s) {
		myStage = s;
		myMainMenu = new GamePlayMenu(s, this);
		myStage.setScene(myMainMenu.getMenuScene());
		myStage.show();
	}

	@Override
	public void playGame(Game game) {
		myStage.close();
		GamePlayer player = new GamePlayer (game, this);
		myStage.setScene(player.getGamePlayScene());
		myStage.show();
	}

	@Override
	public void returnToMenu() {
		myStage.close();
		myStage.setScene(myMainMenu.getMenuScene());
		myStage.show();
	}
	
}
