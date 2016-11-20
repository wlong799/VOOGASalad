package authoring;

import java.util.ArrayList;
import java.util.List;

import game_object.framework.Game;
import game_object.level.Level;

public class AuthorEnvironment implements IAuthorEnvironment {
	
	private List<Game> games;
	private Game currentGame;
	private Level currentLevel;
	
	public AuthorEnvironment() {
		games = new ArrayList<>();
	}

    @Override
    public void addGame(Game game) {
        games.add(game);
    }

    @Override
    public void setCurrentGame(int index) {
        if (index < 0 || index >= games.size()) {
        	throw new IllegalArgumentException("index for level out of range");
        }
        currentGame = games.get(index);
    }

    @Override
    public Game getCurrentGame() {
        return currentGame;
    }

    @Override
    public void addLevel(Level level) {
    	if (currentGame == null) {
    		throw new IllegalArgumentException("no current game");
    	}
        currentGame.addLevel(level);
    }

    @Override
    public void setCurrentLevel(int index) {
    	if (currentGame == null) {
    		throw new IllegalArgumentException("no current game");
    	}
    	if (index < 0 || index >= currentGame.getAllLevels().size()) {
    		throw new IllegalArgumentException("index for level out of range");
    	}
    	currentLevel = currentGame.getAllLevels().get(index);
    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
    }

    @Override
    public void setLanguage(String lang) {
        // TODO Auto-generated method stub

    }

}
