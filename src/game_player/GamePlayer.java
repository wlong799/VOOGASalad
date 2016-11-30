package game_player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import authoring.view.canvas.SpriteImageView;
import game_engine.GameEngine;
import game_engine.GameEngine_Game;
import game_object.LevelGenerator;
import game_object.background.Background;
import game_object.core.ISprite;
import game_object.framework.Game;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.*;
import game_object.acting.*;

/**
 * @author samuelcurtis
 *
 */
public class GamePlayer implements IGamePlayer {
	private Game myCurrentGame;
	private GameEngine myGameEngine;
	private Stage myStage;
	private Scene myScene;
	private Pane myRoot;
	private Set<KeyEvent> myKeysPressed;
	

	public GamePlayer(Stage s, Game game){
		myRoot = new Pane();
		myScene = new Scene(myRoot);
		myStage = s;
		myStage.setScene(myScene);
		myKeysPressed = new HashSet<KeyEvent>();
		myCurrentGame = game;
		myGameEngine = new GameEngine(myCurrentGame.getAllLevels().get(0));
		//setGame("");
		setSceneKeyListener();
		start();
	}


	private void setSceneKeyListener() {
		myScene.setOnKeyPressed(
				new EventHandler<javafx.scene.input.KeyEvent>()
				{
					@Override
					public void handle(javafx.scene.input.KeyEvent event) {
						KeyEvent currEvent = new KeyEvent(event.getCode());
						if(!myKeysPressed.contains(currEvent)){
							myKeysPressed.add(currEvent);
						}
					}
				});

		myScene.setOnKeyReleased(
				new EventHandler<javafx.scene.input.KeyEvent>()
				{
					@Override
					public void handle(javafx.scene.input.KeyEvent event) {
						KeyEvent currEvent = new KeyEvent(event.getCode());
						if(myKeysPressed.contains(currEvent)){
							myKeysPressed.remove(currEvent);
						}
					}
				});

	}


	//This will eventually already take a game object in from a serializer
	@Override
	public void setGame(String gameName) {
		myCurrentGame = new Game();
		//myGameEngine = new GameEngine_Game(myCurrentGame);
	}

	@Override
	public void start() {
		final long startNanoTime = System.nanoTime();
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
				myGameEngine.update(t);
				renderSprites();
			}
		}.start();
		myStage.show();
	}

	private void renderSprites() {
		myRoot.getChildren().clear();
		//ImageView background = getBackGroundImage();
		//myRoot.getChildren().add(background);
		for(ISpriteVisualization currentSprite : myGameEngine.getSprites()){
			Image currentSpriteImage = new Image(currentSprite.getImagePath());
			ImageView currentSpriteView = new ImageView(currentSpriteImage);
			currentSpriteView.setFitWidth(currentSprite.getWidthForVisualization());
			currentSpriteView.setFitHeight(currentSprite.getWidthForVisualization());
			currentSpriteView.setLayoutX(currentSprite.getXForVisualization());
			currentSpriteView.setLayoutY(currentSprite.getYForVisualization());
			myRoot.getChildren().add(currentSpriteView);
		}
	}
	

	private ImageView getBackGroundImage() {
		Background imgPath = myGameEngine.getBackground();
		Image bckGrdImg = new Image(imgPath.getImgPaths().get(0));
		return new ImageView(bckGrdImg);
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
