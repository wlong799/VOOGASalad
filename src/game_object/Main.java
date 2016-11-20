package game_object;

import game_engine.GameEngine;
import game_object.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main {
    public static void main(String[] args){
        Level l = LevelGenerator.getTestLevelA();
        System.out.println(l.getHeros());
        GameEngine object = new GameEngine(l);
        for(int i = 0; i < 700; i ++){
            double time = (1.0/60.0);
            System.out.println(time);
            object.update(time);
        }
        /*
        KeyFrame frame = new KeyFrame(Duration.millis(1000/60),
                                      e -> object.update(1/60));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        System.out.println(animation.getStatus());
        */
    }
}
