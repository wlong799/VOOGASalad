package test;

import game_player.GamePlayManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameMenuTest extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GamePlayManager testManager = new GamePlayManager();
		testManager.start(primaryStage);
		
	}

}
