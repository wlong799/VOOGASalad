package authoring.controller.run;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import authoring.AuthoringController;
import authoring.view.run.TestGameView;
import game_engine.GameEngine;
import game_engine.GameEngine_Game;
import game_engine.IGameEngine;
import game_object.LevelGenerator;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.Event;
import game_object.acting.KeyEvent;
import game_object.character.Hero;
import game_object.core.ISprite;
import game_object.core.Game;
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
    private IGameEngine myGameEngine;

    private KeyFrame frame;
    private Timeline animation;

    private Level myLevel;
    private Map<ISpriteVisualization, ImageView> spriteViewMap;
    private Map<ISpriteVisualization, String> imagePathMap;
    private Hero hero = null;
    private Set<KeyEvent> currentlyPressedKeys;

    public TestGameController (AuthoringController topController) {
        myTopController = topController;
        myTestView = new TestGameView(topController);
    }

    public void showTestGame () {
        Game testGame = LevelGenerator.getTestGame();
        myLevel = testGame.getCurrentLevel();//myTopController.getEnvironment().getCurrentLevel();
        findHero();
        //myLevel.init();
        myGameEngine = new GameEngine_Game(testGame);
        //myGameEngine.suppressLogDebug();

        myTestView.clearSpriteViews();
        spriteViewMap = new HashMap<ISpriteVisualization, ImageView>();
        initSpriteMap();
        currentlyPressedKeys = new HashSet<KeyEvent>();
        frame = new KeyFrame(Duration.millis(1000.0 / 60.0),
                             new EventHandler<ActionEvent>() {
                                 @Override
                                 public void handle (ActionEvent event) {
                                     myLevel = testGame.getCurrentLevel();
                                     System.out.println(myLevel + " current display level");
                                     myGameEngine.setInputList(currentlyPressedKeys);
                                     myGameEngine.update(5.0 / 60.0);
                                     if(hero!=myLevel.getHeros().get(0)){
                                         myTestView.clearSpriteViews();
                                         initSpriteMap();
                                         findHero();
                                     }
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

    public IGameEngine getEngine () {
        return myGameEngine;
    }

    private void findHero () {
        for (ISprite sp : myLevel.getAllSprites()) {
            if (sp instanceof Hero) {
                hero = (Hero) sp;
            }
        }
    }
    private void initSpriteMap(){
        spriteViewMap.clear();
        for (ISpriteVisualization sp : myLevel.getAllSpriteVisualizations()) {
            ImageView image = new ImageView(sp.getImagePath());
            image.setX(sp.getXForVisualization());
            image.setY(sp.getYForVisualization());

            image.setFitWidth(sp.getWidthForVisualization());
            image.setFitHeight(sp.getHeightForVisualization());
            spriteViewMap.put(sp, image);

            myTestView.addSpriteView(image);
        }
    }
    private void keyTriggers2Controls() {
		if (hero == null) return;
		myTestView.getScene().setOnKeyReleased(event-> {
			for(ActionName name : ActionName.values()){
				ActionTrigger trigger = myLevel.getTriggerWithSpriteAndAction(hero, name);
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
				ActionTrigger trigger = myLevel.getTriggerWithSpriteAndAction(hero, name);
				if (trigger == null) break;
				Event evt = trigger.getEvent();

				if (!(evt instanceof KeyEvent)) break;
				if(event.getCode() == ((KeyEvent)evt).getKeyCode()){
					currentlyPressedKeys.add((KeyEvent)evt);
				}
			}
		});
	}
}