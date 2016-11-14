package usecases;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import voogasalad_overwatch.AuthorEnvironment;
import voogasalad_overwatch.Game;
import voogasalad_overwatch.Sprite;

public class UseCase3 {
    public static void main(String[] args){
        Game game = new Game();
        Sprite sprite = new Sprite();
        AuthorEnvironment ae = new AuthorEnvironment();
        new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event) {
                ae.setLanguage("French");
            }
        };
    }
}
