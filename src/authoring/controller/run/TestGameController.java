package authoring.controller.run;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.AuthoringController;
import authoring.view.run.TestGameView;
import game_engine.GameEngine_Game;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.LevelGenerator;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.Event;
import game_object.acting.KeyEvent;
import game_object.character.Hero;
import game_object.core.Game;
import game_object.core.ISprite;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TestGameController {
	private TestGameView myTestView;
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
	private Hero hero = null;
	private Set<KeyEvent> currentlyPressedKeys;

	public TestGameController (AuthoringController topController) {
		myTestView = new TestGameView(topController);
		currentlyPressedKeys = new HashSet<>();
		spriteViewMap = new HashMap<>();
		imagePathMap = new HashMap<>();
		running2origin = new HashMap<>();
	}

	public void showTestGame() {
		originalGame = LevelGenerator.getTestGame();
		runningGame = copyGame(originalGame);
		myTestView.updateUI();
		myGameEngine = new GameEngine_Game(runningGame);
		myGameEngine.suppressLogDebug();
		
		clear();
		initFrame();
		initAnimation();
		myTestView.updateLayout();
		myTestView.show();
	}
	
	public void setParameter(PhysicsParameterSetOptions option, double value) {
		if (originalLevel == null || runningLevel == null) return;
		originalLevel.getPhysicsParameters().set(option, value);
		runningLevel.getPhysicsParameters().set(option, value);
	}
	
	private void clear() {
		myTestView.clearSpriteViews();
		hero = null;
		currentlyPressedKeys.clear();
		spriteViewMap.clear();
		imagePathMap.clear();
		running2origin.clear();
	}
	
	private void initFrame() {
		frame = new KeyFrame(Duration.millis(1000.0 / 60.0),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				Level currentLevel = runningGame.getCurrentLevel();
				if (runningLevel != currentLevel) {
					runningLevel = currentLevel;
					clear();
					initSpriteMap();
					findHero();
					keyTriggers2Controls();
				}
				myGameEngine.setInputList(currentlyPressedKeys);
				myGameEngine.update(5.0 / 60.0);
				updatePositions();
			}
		});
	}
	
	private void updatePositions() {
		for (ISpriteVisualization sprite : myGameEngine.getSprites()) {
			//TODO: need to take care the case where new sprites are created (projectiles e.g.)
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

	private void findHero () {
		for (ISprite sp : runningLevel.getAllSprites()) {
			if (sp instanceof Hero) {
				hero = (Hero) sp;
			}
		}
	}

	private void initSpriteMap() {
		for (ISpriteVisualization sp : runningLevel.getAllSpriteVisualizations()) {
			ImageView image = createNewImageViewForSprite(sp);
			spriteViewMap.put(sp, image);
			myTestView.addSpriteView(image);
		}
	}

	private void keyTriggers2Controls() {
		if (hero == null) return;
		myTestView.getScene().setOnKeyReleased(event-> {
			for(ActionName name : ActionName.values()){
				ActionTrigger trigger = runningLevel.getTriggerWithSpriteAndAction(hero, name);
				if (trigger == null) break;
				Event evt = trigger.getEvent();

				if (!(evt instanceof KeyEvent)) break;
				if(event.getCode() == ((KeyEvent)evt).getKeyCode()){
					currentlyPressedKeys.remove((KeyEvent)evt);
				}
			}
		});

		myTestView.getScene().setOnKeyPressed(event -> {
			for (ActionName name : ActionName.values()) {
				ActionTrigger trigger = runningLevel.getTriggerWithSpriteAndAction(hero, name);
				if (trigger == null) break;
				Event evt = trigger.getEvent();

				if (!(evt instanceof KeyEvent)) break;
				if(event.getCode() == ((KeyEvent)evt).getKeyCode()){
					currentlyPressedKeys.add((KeyEvent)evt);
				}
			}
		});
	}

	private Game copyGame(Game game) {
		XStream mySerializer = new XStream(new DomDriver());
		return (Game)mySerializer.fromXML(mySerializer.toXML(game));
	}

}