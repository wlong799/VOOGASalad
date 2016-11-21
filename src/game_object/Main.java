package game_object;

import java.io.File;
import java.util.List;
import game_engine.GameEngine;
import game_object.level.Level;
import game_object.visualization.ISpriteVisualization;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
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
        Scene scene = new Scene(g, 600, 600, Color.WHITE);
        s.setScene(scene);
        s.show();
        
        KeyFrame frame = new KeyFrame(Duration.millis(1000.0 / 60.0),
                                      new EventHandler<ActionEvent>() {
                                          @Override
                                          public void handle (ActionEvent event) {
                                              object.update(5.0/ 60.0);
                                              List<ISpriteVisualization> sprites =
                                                      l.getAllSpriteVisualizations();
                                              g.getChildren().clear();
                                              for (ISpriteVisualization s : sprites) {
                                                  //System.out.println(s.getImagePath());
                                                  
                                                  //Image im = new Image(new File(System.getProperty("user.dir")+s.getImagePath()).toURI().toString());
                                                  ImageView image =
                                                          new ImageView(new File(s.getImagePath()).toURI().toString());//,
                                                  image.setX(s.getXForVisualization());
                                                  image.setY(s.getYForVisualization());
                                                  
                                                  image.setFitWidth(s.getWidthForVisualization());
                                                  image.setFitHeight(s.getHeightForVisualization());
                                                  
                                                  g.getChildren().add(image);
                                              }
                                          }
                                      });

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
}
