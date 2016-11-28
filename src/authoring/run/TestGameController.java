package authoring.run;

import java.util.HashMap;
import java.util.Map;

import authoring.AuthoringController;
import game_engine.GameEngine;
import game_object.character.Hero;
import game_object.core.ISprite;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TestGameController {

	private AuthoringController myTopController;
	private TestGameView myTestView;
	private GameEngine myGameEngine;
	
	private KeyFrame frame;
	private Timeline animation;

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

		addHeroControls(level);
		myTestView.updateLayout();
		myTestView.show();
	}
	
	public GameEngine getEngine() {
		return myGameEngine;
	}
	
	private void addHeroControls(Level l) {
		for (ISprite sp : l.getAllSprites()) {
			if (sp instanceof Hero) {
				Hero hero = (Hero) sp;
				myTestView.getScene().setOnKeyPressed(event -> {
					switch (event.getCode()) {
					case W:
						hero.jumpUp();
						break;
					case A:
						hero.moveLeft();
						break;
					case S:
						break;
					case D:
						hero.moveRight();
						break;
					default:
						break;
					}
				});
				break;
			}
		}
	}

}
