package game_player;

import javafx.stage.Stage;
import java.io.Serializable;
import java.util.Random;
import java.util.function.UnaryOperator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_object.framework.Game;
import javafx.animation.KeyFrame;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlayManager {
	private Stage myStage;
	private GamePlayMenu myMainMenu;
	private XStream mySerializer = new XStream(new DomDriver());
	private Game myCurrentGame;
	
	public void start(Stage s) {
		myStage = s;
		chooseGame();
	}
	
	public void chooseGame(){
		myCurrentGame = (Game)mySerializer.fromXML("/sample_game/sample_game.xml");
	}
	

}
