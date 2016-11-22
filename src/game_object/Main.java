package game_object;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.GameEngine;
import game_object.character.Hero;
import game_object.core.ISprite;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	private Scene scene;
	
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        Level l = LevelGenerator.getTestLevelA();
        l.init();
        System.out.println(l.getHeros());
        GameEngine object = new GameEngine(l);
        Stage s = new Stage();
        Group g = new Group();
        scene = new Scene(g, 600, 600, Color.WHITE);
        s.setScene(scene);
        s.show();
        Map<ISpriteVisualization, ImageView> spriteViewMap =
                new HashMap<ISpriteVisualization, ImageView>();
        for (ISpriteVisualization sp : l.getAllSpriteVisualizations()) {
            ImageView image =
                    new ImageView(new File(sp.getImagePath()).toURI().toString());// ,
            image.setX(sp.getXForVisualization());
            image.setY(sp.getYForVisualization());

            image.setFitWidth(sp.getWidthForVisualization());
            image.setFitHeight(sp.getHeightForVisualization());
            spriteViewMap.put(sp, image);
            g.getChildren().add(image);
        }
        addHeroControls(l);
        KeyFrame frame = new KeyFrame(Duration.millis(1000.0 / 60.0),
                                      new EventHandler<ActionEvent>() {
                                          @Override
                                          public void handle (ActionEvent event) {
                                              object.update(5.0 / 60.0);
                                              List<ISpriteVisualization> sprites =
                                                      l.getAllSpriteVisualizations();
                                              // g.getChildren().clear();
                                              for (ISpriteVisualization sprite : spriteViewMap
                                                      .keySet()) {
                                                  // System.out.println(s.getImagePath());

                                                  // Image im = new Image(new
                                                  // File(System.getProperty("user.dir")+s.getImagePath()).toURI().toString());
                                                  spriteViewMap.get(sprite).setX(sprite.getXForVisualization());
                                                  spriteViewMap.get(sprite).setY(sprite.getYForVisualization());
                                              }
                                          }
                                      });

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    private void addHeroControls(Level l) {
    	for (ISprite sp : l.getAllSprites()) {
    		if (sp instanceof Hero) {
    			Hero hero = (Hero) sp;
    			scene.setOnKeyPressed(event -> {
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
