package game_player;

import java.io.File;
import java.util.ResourceBundle;

import game_object.core.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.ResourceBundles;

/**
 * @author samuelcurtis, billyu
 *This class is the front end view for game engine. It takes in a game object, and then repeatedly 
 *calls methods from the IGameEngine interface at a specific framerate in order to receive all of the objects
 *it needs to render onto the screen. It is  also responsible for detecting all user key input and 
 *sending that to the game engine.
 */

public class GamePlayer {
	private ResourceBundle myResources = ResourceBundles.languageProperties;
	private Stage myStage;
	private Scene myScene;
	private GameRunner myRunner;
	private Game myCurrentGame;
	private Group myRoot;

	public GamePlayer(Stage s, Game game) {
		myStage = s;
		myRoot = new Group();
		myScene = new Scene(myRoot, game.getScreenSize().getWidth(), game.getScreenSize().getHeight());
		setSceneCSS();
		myRunner = new GameRunner(myScene, game, level->{});
		myRoot.getChildren().add(myRunner.getHUDController().getView());
		myRoot.getChildren().add(myRunner.getRunningView().getViews());
		myStage.setScene(myScene);
		myStage.show();
	}

	private void setSceneCSS() {
		File f = new File(myResources.getString("HUDCSSFile"));
		myScene.getStylesheets().clear();
		myScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
	}

}
