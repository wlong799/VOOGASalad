package usecases;

import com.thoughtworks.xstream.XStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import voogasalad_overwatch.Game;
import voogasalad_overwatch.Sprite;

public class UseCase15 {
    public static void main(String[] args){
        Game game = new Game();
        Sprite sprite = new Sprite();
        new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event) {
                game.editSprite(sprite);
            }
        };
    }
}
