package usecases;

import com.thoughtworks.xstream.XStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import voogasalad_overwatch.Game;
import voogasalad_overwatch.IAuthorEnvironment;


public class UseCase4{
    public static void main(String[] args){
        Game game = new Game();
        XStream xs = new XStream();
        new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event) {
                xs.toXML(game);
            }
        };
        
    }
}
