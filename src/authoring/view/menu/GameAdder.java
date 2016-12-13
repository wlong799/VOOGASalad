package authoring.view.menu;

import java.util.Optional;
import java.util.ResourceBundle;

import authoring.AuthorEnvironment;
import game_object.core.Game;
import game_object.level.Level;
import javafx.scene.control.TextInputDialog;
import resources.ResourceBundles;

public class GameAdder {
	private static ResourceBundle myLanguageResourceBundle = ResourceBundles.languageProperties;
	
	public void addGame(AuthorEnvironment environment) {
		Game game = new Game(myLanguageResourceBundle.getString("gameDefaultName"));
		game.addLevel(getNewLevel(game));
		environment.addGame(game);
	}
	
	public boolean addLevel(AuthorEnvironment environment) {
		Game currentGame = environment.getCurrentGame();
		Level newLevel = getNewLevel(currentGame);
		return environment.addLevel(newLevel);
	}
	
	private Level getNewLevel(Game game) {
		String id = getIDFromUser();
		Level newLevel = new Level(game, id);
		newLevel.replaceAllHerosAndTriggersWithLevel(game.getCurrentLevel());
		return newLevel;
	}
	
	private String getIDFromUser() {
    	TextInputDialog dialog = new TextInputDialog(myLanguageResourceBundle.getString("exitingLevel"));
    	dialog.setTitle(myLanguageResourceBundle.getString("levelID"));
    	dialog.setHeaderText(myLanguageResourceBundle.getString("inputID"));
    	dialog.setContentText(myLanguageResourceBundle.getString("forGreater"));

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		return result.get();
    	}
    	return null;
    }

}
