package voogasalad_overwatch;

import java.util.List;

import game_object.Game;
import game_object.Level;

public class AuthorEnvironment implements IAuthorEnvironment {
	
	private List<Game> games;
	private Game currentGame;
	private Level currentLevel;

    @Override
    public void addGame(Game game) {
        games.add(game);
    }

    @Override
    public void setCurrentGame(Game game) {
        currentGame = game;
    }

    @Override
    public Game getCurrentGame() {
        return currentGame;
    }

    @Override
    public void addLevel(Level level) {
        currentGame.addLevel(level);
    }

    @Override
    public void setCurrentLevel(Level level) {
    	currentLevel = level;
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
