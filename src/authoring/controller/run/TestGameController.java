package authoring.controller.run;

import java.util.HashMap;
import java.util.Map;

import authoring.AuthoringController;
import authoring.view.run.TestGameView;
import game_engine.GameEngine;
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
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class TestGameController {

	private AuthoringController myTopController;
	private TestGameView myTestView;
	private GameEngine myGameEngine;

	private KeyFrame frame;
	private Timeline animation;

	private Level myLevel;
	private Map<ISpriteVisualization, ImageView> spriteViewMap;
	private Hero hero = null;

	public TestGameController(AuthoringController topController) {
		myTopController = topController;
		myTestView = new TestGameView(topController);
	}

	public void showTestGame() {
		myLevel = myTopController.getEnvironment().getCurrentLevel();
		findHero();
		myLevel.init();
		myGameEngine = new GameEngine(myLevel);
		myGameEngine.suppressLogDebug();

		myTestView.clearSpriteViews();
		spriteViewMap = new HashMap<ISpriteVisualization, ImageView>();
		for (ISpriteVisualization sp : myLevel.getAllSpriteVisualizations()) {
			ImageView image = new ImageView(sp.getImagePath());
			image.setX(sp.getXForVisualization());
			image.setY(sp.getYForVisualization());

			image.setFitWidth(sp.getWidthForVisualization());
			image.setFitHeight(sp.getHeightForVisualization());
			spriteViewMap.put(sp, image);

			myTestView.addSpriteView(image);
		}

		frame = new KeyFrame(Duration.millis(1000.0 / 60.0),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myGameEngine.update(5.0 / 60.0);
				for (ISpriteVisualization sprite : spriteViewMap.keySet()) {
					ImageView imgView = spriteViewMap.get(sprite);
					if (sprite.facingLeft()) {
						imgView.setImage(new Image(sprite.getImagePathLeft()));
					}
					else if (sprite.facingRight()) {
						imgView.setImage(new Image(sprite.getImagePathRight()));
					}
					imgView.setX(sprite.getXForVisualization());
					imgView.setY(sprite.getYForVisualization());
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

	public GameEngine getEngine() {
		return myGameEngine;
	}
	
	private void findHero() {
		for (ISprite sp : myLevel.getAllSprites()) {
			if (sp instanceof Hero) {
				hero = (Hero) sp;
			}
		}
	}

	private void keyTriggers2Controls() {
		if (hero == null) return;
		myTestView.getScene().setOnKeyPressed(event -> {
			for (ActionName name : ActionName.values()) {
				ActionTrigger trigger = myLevel.getTriggerWithSpriteAndAction(hero, name);
				if (trigger == null) break;
				Event evt = trigger.getEvent();
				if (!(evt instanceof KeyEvent)) break;
				KeyCode code = ((KeyEvent) evt).getKeyCode();
				if (event.getCode() == code) {
					switch (name) {
					case JUMP:
						hero.jumpUp();
						break;
					case MOVE_LEFT:
						hero.moveLeft();
						break;
					case MOVE_RIGHT:
						hero.moveRight();
						break;
					case SHOOT:
						//todo
						break;
					default:
						break;
					}

				}
			}
		});
	}
	
}
