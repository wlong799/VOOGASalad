package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_engine.GameEngine_Game;
import game_object.acting.Event;
import game_object.acting.KeyEvent;
import game_object.background.Background;
import game_object.core.Game;
import game_object.core.ISprite;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import game_player.image.ImageRenderer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * @author billyu
 * frontend game running engine
 * takes a scene and a game
 * UI Group root can be accessed using getRunningView().getViews()
 */
public class GameRunner implements IEndListener{
	
	private GameEngine_Game myGameEngine;

	private KeyFrame frame;
	private Timeline animation;

	private Game originalGame;
	private Game runningGame;
	private Level originalLevel;
	private Level runningLevel;
	
	private Map<ISpriteVisualization, ImageView> spriteViewMap;
	private Map<Level, Level> running2origin;
	private Set<Event> currentlyPressedKeys;
	
	private GameRunningView myView;
	private Scene myScene;
	private Consumer<Level> myLevelChangeHandler;
	private ImageRenderer myRenderer;
	private HUDController myHudController;
	private IEndListener myEndListener;

	public GameRunner(Scene s, Game game, Consumer<Level> levelChangeHandler, IEndListener listener) {
		myScene = s;
		originalGame = game;
//		originalGame.getAllLevels().forEach(l->{
//		    List<ISprite> sprites = new ArrayList<ISprite>(l.getAllSprites());
//		    l.getAllSprites().clear();
//		    sprites.forEach(l::addSprite);
//		});
		currentlyPressedKeys = new HashSet<>();
		spriteViewMap = new HashMap<>();
		running2origin = new HashMap<>();
		myView = new GameRunningView();
		myLevelChangeHandler = levelChangeHandler;
		myRenderer = new ImageRenderer();
		myEndListener = listener;
		init();
	}
	
	public HUDController getHUDController(){
		return myHudController;
	}
	
	public GameRunningView getRunningView() {
		return myView;
	}
	
	public Level getOriginalLevel() {
		return originalLevel;
	}
	
	public Level getRunningLevel() {
		return runningLevel;
	}

	private void init() {
		runningGame = copyGame(originalGame);
		myGameEngine = new GameEngine_Game(runningGame, this);
		myGameEngine.suppressLogDebug();
		myHudController = new HUDController(runningGame);
		
		clear();
		initRunning2Origin();
		initFrame();
		initAnimation();
	}

	private void initRunning2Origin() {
		List<Level> origin = originalGame.getAllLevelsReadOnly();
		List<Level> running = runningGame.getAllLevelsReadOnly();
		for (int i = 0; i < origin.size(); i++) {
			running2origin.put(running.get(i), origin.get(i));
		}
	}

	private void clear() {
		myView.clearSpriteViews();
		currentlyPressedKeys.clear();
		spriteViewMap.clear();
	}

	private void initFrame() {
		frame = new KeyFrame(Duration.millis(1000.0 / runningGame.getFPS()),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				if (myGameEngine.isShutDown()) return;
				Level currentLevel = runningGame.getCurrentLevel();
				if (runningLevel != currentLevel) {
					runningLevel = currentLevel;
					originalLevel = running2origin.get(runningLevel);
					myLevelChangeHandler.accept(originalLevel);
					clear();
					initBackground();
					initHud();
					keyTriggers2Controls();
				}
				myGameEngine.setInputList(currentlyPressedKeys);
				myGameEngine.update(5.0 / runningGame.getFPS());
				update();
			}
		});
	}

	private void update() {
		for (ISpriteVisualization sprite : myGameEngine.getSprites()) {
			if (!spriteViewMap.containsKey(sprite)) {
				//new sprite
				addSpriteViewWithSprite(sprite);
			} else {
				spriteViewMap.get(sprite).setScaleX(sprite.isFacingLeft() ? 1 : -1);
			}
			spriteViewMap.get(sprite).setX(sprite.getXForVisualization());
			spriteViewMap.get(sprite).setY(sprite.getYForVisualization());
		}
		
		myGameEngine.getSpritesOffScreen().forEach(s->{
		    s.getXForVisualization();
		    s.getYForVisualization();
		});
		
		myHudController.updateStatisticsMap();
		//remove what's not returned from game engine
		Set<ISpriteVisualization> removing = new HashSet<>(spriteViewMap.keySet());
		removing.removeAll(myGameEngine.getSprites());
		for (ISpriteVisualization sprite : removing) {
			myView.removeSpriteView(spriteViewMap.get(sprite));
			spriteViewMap.remove(sprite);
		}
	}
	
	private void addSpriteViewWithSprite(ISpriteVisualization sprite) {
		ImageView image = createNewImageViewForSprite(sprite);
		spriteViewMap.put(sprite, image);
		myView.addSpriteView(image);
	}
	
	private ImageView createNewImageViewForSprite(ISpriteVisualization sprite) {
		Image image = new Image(sprite.getImagePath());
		return myRenderer.render(
				image, 
				sprite.getImageStyle(), 
				sprite.getWidthForVisualization(), 
				sprite.getHeightForVisualization());
	}
	
	private void initBackground() {
		Background background = myGameEngine.getBackground();
		if (background.getImagePaths().size() < 1) return;
		ImageView bckGrdImg = new ImageView(background.getImagePaths().get(0));
		bckGrdImg.setFitWidth(runningLevel.getDimension().getWidth());
		bckGrdImg.setFitWidth(runningLevel.getDimension().getHeight());
		myView.addSpriteView(bckGrdImg);
	}
	
	private void initHud() {
		myView.getViews().getChildren().add(myHudController.getView());
	}

	private void initAnimation() {
		if (animation != null) {
			animation.stop();
		}
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void keyTriggers2Controls() {
		myScene.setOnKeyReleased(event-> currentlyPressedKeys.remove(new KeyEvent(event.getCode())));
		myScene.setOnKeyPressed(event -> currentlyPressedKeys.add(new KeyEvent(event.getCode())));
	}

	private Game copyGame(Game game) {
		XStream mySerializer = new XStream(new DomDriver());
		return (Game)mySerializer.fromXML(mySerializer.toXML(game));
	}

	@Override
	public void onEnd() {
		animation.stop();
		myEndListener.onEnd();
		
	}

}
