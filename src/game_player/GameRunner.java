package game_player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_engine.GameEngine_Game;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.Event;
import game_object.acting.KeyEvent;
import game_object.background.Background;
import game_object.character.Hero;
import game_object.core.Game;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import game_player.image.ImageRenderer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
public class GameRunner {
	
	private GameEngine_Game myGameEngine;

	private KeyFrame frame;
	private Timeline animation;

	private Game originalGame;
	private Game runningGame;
	private Level originalLevel;
	private Level runningLevel;
	
	private Map<ISpriteVisualization, ImageView> spriteViewMap;
	private Map<ISpriteVisualization, String> imagePathMap;
	private Map<Level, Level> running2origin;
	private Set<KeyEvent> currentlyPressedKeys;
	
	private GameRunningView myView;
	private Scene myScene;
	private Consumer<Level> myLevelChangeHandler;
	private ImageRenderer myRenderer;

	public GameRunner(Scene s, Game game, Consumer<Level> levelChangeHandler) {
		myScene = s;
		originalGame = game;
		currentlyPressedKeys = new HashSet<>();
		spriteViewMap = new HashMap<>();
		imagePathMap = new HashMap<>();
		running2origin = new HashMap<>();
		myView = new GameRunningView();
		myLevelChangeHandler = levelChangeHandler;
		myRenderer = new ImageRenderer();
		init();
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
		myGameEngine = new GameEngine_Game(runningGame);
		myGameEngine.suppressLogDebug();

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
		imagePathMap.clear();
	}

	private void initFrame() {
		frame = new KeyFrame(Duration.millis(1000.0 / runningGame.getFPS()),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				Level currentLevel = runningGame.getCurrentLevel();
				if (runningLevel != currentLevel) {
					runningLevel = currentLevel;
					originalLevel = running2origin.get(runningLevel);
					myLevelChangeHandler.accept(originalLevel);
					clear();
					initBackground();
					initSpriteMap();
					keyTriggers2Controls();
				}
				myGameEngine.setInputList(currentlyPressedKeys);
				myGameEngine.update(5.0 / runningGame.getFPS());
				updatePositions();
			}
		});
	}

	private void updatePositions() {
		for (ISpriteVisualization sprite : myGameEngine.getSprites()) {
			//TODO: need to take care the case where new sprites are created (projectiles e.g.)
			if (!spriteViewMap.containsKey(sprite)) continue;
			if (!imagePathMap.containsKey(sprite)
					|| !imagePathMap.get(sprite).equals(sprite.getImagePath())) {
				// image path changed (e.g. facing changed)
				imagePathMap.put(sprite, sprite.getImagePath());
				spriteViewMap.get(sprite).setImage(new Image(sprite.getImagePath()));
			}
			spriteViewMap.get(sprite).setX(sprite.getXForVisualization());
			spriteViewMap.get(sprite).setY(sprite.getYForVisualization());
		}
	}
	
	private void initBackground() {
		Background background = myGameEngine.getBackground();
		if (background.getImagePaths().size() < 1) return;
		ImageView bckGrdImg = new ImageView(background.getImagePaths().get(0));
		bckGrdImg.setFitWidth(runningLevel.getLevelDimension().getWidth());
		bckGrdImg.setFitWidth(runningLevel.getLevelDimension().getHeight());
		myView.addSpriteView(bckGrdImg);
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

	private ImageView createNewImageViewForSprite(ISpriteVisualization sprite) {
		String imagePath = sprite.getImagePath();
		ImageView image = new ImageView(imagePath);
		image.setX(sprite.getXForVisualization());
		image.setY(sprite.getYForVisualization());

		image.setFitWidth(sprite.getWidthForVisualization());
		image.setFitHeight(sprite.getHeightForVisualization());
		return image;
	}

	private void initSpriteMap() {
		for (ISpriteVisualization sp : runningLevel.getAllSpriteVisualizations()) {
			ImageView image = createNewImageViewForSprite(sp);
			spriteViewMap.put(sp, image);
			myView.addSpriteView(image);
		}
	}

	private void keyTriggers2Controls() {
		myScene.setOnKeyReleased(event-> {
			for (Hero hero : runningLevel.getHeros()) {
				for(ActionName name : ActionName.values()) {
					ActionTrigger trigger = runningLevel.getTriggerWithSpriteAndAction(hero, name);
					if (trigger == null) break;
					Event evt = trigger.getEvent();

					if (!(evt instanceof KeyEvent)) break;
					if(event.getCode() == ((KeyEvent)evt).getKeyCode()){
						currentlyPressedKeys.remove((KeyEvent)evt);
					}
				}
			}
		});
		myScene.setOnKeyPressed(event -> {
			for (Hero hero : runningLevel.getHeros()) {
				for (ActionName name : ActionName.values()) {
					ActionTrigger trigger = runningLevel.getTriggerWithSpriteAndAction(hero, name);
					if (trigger == null) break;
					Event evt = trigger.getEvent();

					if (!(evt instanceof KeyEvent)) break;
					if(event.getCode() == ((KeyEvent)evt).getKeyCode()){
						currentlyPressedKeys.add((KeyEvent)evt);
					}
				}
			}
		});
	}

	private Game copyGame(Game game) {
		XStream mySerializer = new XStream(new DomDriver());
		return (Game)mySerializer.fromXML(mySerializer.toXML(game));
	}
	
}
