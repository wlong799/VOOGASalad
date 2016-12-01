package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.view.canvas.SpriteImageView;
import game_engine.GameEngine;
import game_engine.GameEngine_Game;
import game_object.LevelGenerator;
import game_object.background.Background;
import game_object.character.Hero;
import game_object.core.*;
import game_object.core.ISprite;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.*;
import game_object.acting.*;

/**
 * @author samuelcurtis
 *This class is the front end view for game engine. It takes in a game object, and then repeatedly 
 *calls methods from the IGameEngine interface at a specific framerate in order to receive all of the objects
 *it needs to render onto the screen. It is  also responsible for detecting all user key input and 
 *sending that to the game engine.
 */

public class GamePlayer implements IGamePlayer {
	private Game myCurrentGame;
	private GameEngine_Game myGameEngine;
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	private Set<KeyEvent> myKeysPressed;
	private KeyFrame frame;
	private Timeline animation;
	private Map<ISpriteVisualization, ImageView> spriteViewMap;
	private Map<ISpriteVisualization, String> imagePathMap;

	

	public GamePlayer(Stage s, Game game){
		myRoot = new Group();
		myScene = new Scene(myRoot,game.getScreenSize().getWidth(),game.getScreenSize().getHeight());
		myStage = s;
		myStage.setScene(myScene);
		myKeysPressed = new HashSet<KeyEvent>();
		myCurrentGame = game;
		myGameEngine = new GameEngine_Game(game);
		//setGame("");
		setSceneKeyListener();
		showTestGame();
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
	
	public void showTestGame () {
		spriteViewMap = new HashMap<>();
		imagePathMap = new HashMap<>(); 
		for (ISpriteVisualization sp : myGameEngine.getSprites()) {
			String imagePath = sp.getImagePath();
			ImageView image = createNewImageViewForSprite(sp);
			myRoot.getChildren().add(image);
			spriteViewMap.put(sp, image);
			imagePathMap.put(sp, imagePath);
		}
		frame = new KeyFrame(Duration.millis(1000.0 / 60.0),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myGameEngine.setInputList(myKeysPressed);
				myGameEngine.update(5.0 / 60.0);
				for (ISpriteVisualization sprite : myGameEngine.getSprites()) {
					//TODO: need to take care the case where new sprites are created (projectiles e.g.)
					if (!imagePathMap.get(sprite).equals(sprite.getImagePath())) {
						// image path changed (e.g. facing changed)
						imagePathMap.put(sprite, sprite.getImagePath());
						spriteViewMap.get(sprite).setImage(new Image(sprite.getImagePath()));
					}
					spriteViewMap.get(sprite)
					.setX(sprite.getXForVisualization());
					spriteViewMap.get(sprite)
					.setY(sprite.getYForVisualization());
				}
			}
		});

		if (animation != null) {
			animation.stop();
		}
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		myStage.show();
	}

	private ImageView getBackGroundImage() {
		Background imgPath = myGameEngine.getBackground();
		System.out.println(imgPath.getImagePaths().get(0));
		Image bckGrdImg = new Image(imgPath.getImagePaths().get(0));
		ImageView test = new ImageView(bckGrdImg);
		test.setFitWidth(myScene.getWidth());
		test.setFitWidth(myScene.getHeight());
		return test;
	}
	
	private ImageView createNewImageViewForSprite(ISpriteVisualization sprite) {
		String imagePath = sprite.getImagePath();
		ImageView image = new ImageView(imagePath);
		image.setX(sprite.getXForVisualization());
		image.setY(sprite.getYForVisualization());

		image.setFitWidth(sprite.getWidthForVisualization());
		image.setFitHeight(sprite.getHeightForVisualization());
		return image;
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
