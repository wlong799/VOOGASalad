package authoring.controller.run;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.AuthoringController;
import authoring.view.run.TestGameView;
import game_engine.GameEngine;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.Event;
import game_object.acting.KeyEvent;
import game_object.character.Hero;
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
	private AuthoringController myTopController;
	private TestGameView myTestView;
	private GameEngine myGameEngine;

	private KeyFrame frame;
	private Timeline animation;

	private Level runningLevel;
	private Level originalLevel;
	private Map<ISpriteVisualization, ImageView> spriteViewMap;
	private Map<ISpriteVisualization, String> imagePathMap;
	private Hero hero = null;
	private Set<KeyEvent> currentlyPressedKeys;

	public TestGameController (AuthoringController topController) {
		myTopController = topController;
		myTestView = new TestGameView(topController);
	}

	public void showTestGame () {
		originalLevel = myTopController.getEnvironment().getCurrentLevel();
		runningLevel = copyLevel(originalLevel);
		findHero();
		runningLevel.init();
		myGameEngine = new GameEngine(runningLevel);
		myGameEngine.suppressLogDebug();

		myTestView.clearSpriteViews();
		spriteViewMap = new HashMap<>();
		imagePathMap = new HashMap<>(); 
		for (ISpriteVisualization sp : runningLevel.getAllSpriteVisualizations()) {
			String imagePath = sp.getImagePath();
			ImageView image = createNewImageViewForSprite(sp);
			spriteViewMap.put(sp, image);
			imagePathMap.put(sp, imagePath);
			myTestView.addSpriteView(image);
		}
		currentlyPressedKeys = new HashSet<KeyEvent>();
		frame = new KeyFrame(Duration.millis(1000.0 / 60.0),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myGameEngine.setInputList(currentlyPressedKeys);
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

		keyTriggers2Controls();
		myTestView.updateLayout();
		myTestView.show();
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
	
	public void setParameter(PhysicsParameterSetOptions option, double value) {
		originalLevel.getPhysicsParameters().set(option, value);
	}

	private void findHero () {
		for (ISprite sp : runningLevel.getAllSprites()) {
			if (sp instanceof Hero) {
				hero = (Hero) sp;
			}
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
	
	private Level copyLevel(Level level) {
		XStream mySerializer = new XStream(new DomDriver());
		return (Level)mySerializer.fromXML(mySerializer.toXML(level));
	}
	
}