package game_engine.transition;

import game_object.core.Game;
import game_object.level.Level;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Handles level transitions: the most basic rule -- go to the next level
 * 
 * @author Charlie Wang
 */
public class TransitionManager extends AbstractTransitionManager {

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
            root.getChildren().add(new Label("GAME OVER"));
            root.setAlignment(Pos.CENTER);
            s.setScene(new Scene(root,400,400));
            s.show();
	}
}
