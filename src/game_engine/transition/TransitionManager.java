package game_engine.transition;

import game_object.core.Game;
import game_object.level.Level;
import game_player.GamePlayer;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Handles level transitions: the most basic rule -- go to the next level
 * 
 * @author Charlie Wang
 */
public class TransitionManager extends AbstractTransitionManager {
    
        public static final String GAME_OVER = "GAME OVER!!!";
        public static final String FROWN = "img/frown.png";
        public static final String REPLAY = "Replay";
        public static final String QUIT = "QUIT";
        public static final int SPACING = 50;

	private Level myLevel;

	public TransitionManager(Game game, Level currentLevel) {
		super(game);
		myLevel = currentLevel;
	}

	@Override
	public Level levelWonReturn() {
	    //System.out.println(myLevel.getNextLevel());
	    myLevel = myLevel.getNextLevel();
	    //System.out.println(myLevel.getNextLevel());
	    myGame.setCurrentLevel(myLevel);
	    return myLevel;
		//return myLevel = myLevel.getNextLevel();
	}

	@Override
	public Level levelLostReturn() {
		Level level = myLevel;
		while (level!=null) {
			level=level.getNextLevel();
		}
		showGameOver();
		return level;
	}
	
	@Override
	public Level levelGoOnReturn() {
		return null;
	}

	public void setLevel(Level level) {
		myLevel = level;
	}
	
	private void showGameOver(){
            Stage s = new Stage();
            VBox root = new VBox();
            root.setSpacing(SPACING);
            root.setMaxHeight(Integer.MAX_VALUE);
            Label gameOver = new Label(GAME_OVER);
            gameOver.setFont(new Font(SPACING));
            HBox buttons = new HBox();
            buttons.setMaxWidth(Integer.MAX_VALUE);
            Button replay = new Button(REPLAY);
            Button quit = new Button(QUIT);
            replay.setPrefWidth(myGame.getScreenSize().getWidth()/4);
            quit.setPrefWidth(myGame.getScreenSize().getWidth()/4);
            buttons.setSpacing(SPACING);
            replay.setOnAction((event)->{
                //TODO
                //myGame.setFirstSceneAsLevel(myLevel);
                //GamePlayer g = new GamePlayer(new Stage(),myGame);
            });
            quit.setOnAction((event) -> {
                //TODO
            });
            buttons.getChildren().addAll(replay,quit);
            buttons.setAlignment(Pos.CENTER);
            root.getChildren().addAll(gameOver,new ImageView(FROWN),buttons);
            root.setAlignment(Pos.CENTER);
            s.setScene(new Scene(root,myGame.getScreenSize().getWidth()/2,myGame.getScreenSize().getHeight()/2));
            s.show();
	}
}
