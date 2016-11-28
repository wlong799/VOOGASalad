package authoring.controller.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import authoring.AuthoringController;
import authoring.view.run.TestGameView;
import game_engine.GameEngine;
import game_object.acting.ActionTrigger;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class TestGameController {

	private AuthoringController myTopController;
	private TestGameView myTestView;
	private GameEngine myGameEngine;

	private KeyFrame frame;
	private Timeline animation;

	private Hero hero = null;

	public TestGameController(AuthoringController topController) {
		myTopController = topController;
		myTestView = new TestGameView(topController);
	}

	public void showTestGame() {
		Level level = myTopController.getEnvironment().getCurrentLevel();
		level.init();
		myGameEngine = new GameEngine(level);
		myGameEngine.suppressLogDebug();

		myTestView.clearSpriteViews();
		Map<ISpriteVisualization, ImageView> spriteViewMap =
				new HashMap<ISpriteVisualization, ImageView>();
		for (ISpriteVisualization sp : level.getAllSpriteVisualizations()) {
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
					spriteViewMap.get(sprite).setX(sprite.getXForVisualization());
					spriteViewMap.get(sprite).setY(sprite.getYForVisualization());
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

		keyTriggers2Controls(level);
		myTestView.updateLayout();
		myTestView.show();
	}

	public GameEngine getEngine() {
		return myGameEngine;
	}

	private void keyTriggers2Controls(Level l) {
		for (ISprite sp : l.getAllSprites()) {
			if (sp instanceof Hero) {
				Hero hero = (Hero) sp;
				ArrayList<ActionTrigger> herosActionTrigger = hero.getActionTriggers();
				myTestView.getScene().setOnKeyPressed(event -> {
					switch (event.getCode()) {
						case W:
							//hero;
							break;
						default:
							break;
					}
				});
			}
			
		if (hero == null) return;
		myTestView.getScene().setOnKeyPressed(event -> {
			for (ActionName name : ActionName.values()) {
				ActionTrigger trigger = l.getTriggerWithSpriteAndAction(hero, name);
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
	}

