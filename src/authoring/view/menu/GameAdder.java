package authoring.view.menu;

import java.util.Optional;

import authoring.AuthorEnvironment;
import game_object.core.Game;
import game_object.level.Level;
import javafx.scene.control.TextInputDialog;

public class GameAdder {
	
	public void addGame(AuthorEnvironment environment) {
		Game game = new Game("TODO:id");
		game.addLevel(getNewLevel(game));
		environment.addGame(game);
	}
	
	public void addLevel(AuthorEnvironment environment) {
		Game currentGame = environment.getCurrentGame();
		Level newLevel = getNewLevel(currentGame);
		environment.addLevel(newLevel);
	}
	
	private Level getNewLevel(Game game) {
		String id = getIDFromUser();
		Level newLevel = new Level(game, id);
		return newLevel;
	}
	
	private String getIDFromUser() {
    	TextInputDialog dialog = new TextInputDialog("Exciting Level");
    	dialog.setTitle("ID for New Level");
    	dialog.setHeaderText("Please input the ID of your new level");
    	dialog.setContentText("This is for a greater America.");

    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		return result.get();
    	}
    	return null;
    }

}
