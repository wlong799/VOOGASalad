package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import game_object.core.Game;
import game_object.level.Level;

/**
 * AuthorEnvironment contains all of the games currently available for editing. It extends Observable so that
 * elements within the authoring environment know when they need to updateObservers.
 */
public class AuthorEnvironment extends Observable implements IAuthorEnvironment {
    private List<Game> myAvailableGames;
    private Game myCurrentGame;
    private Level myCurrentLevel;
    private String resourceLanguageFilePath;

    public AuthorEnvironment() {
        myAvailableGames = new ArrayList<>();
    }

    @Override
    public void addGame(Game game) {
        myAvailableGames.add(game);
        setCurrentGame(game);
    }

    @Override
    public void setCurrentGame(int index) {
        if (index < 0 || index >= myAvailableGames.size()) {
            throw new IllegalArgumentException("Game index out of range: " + index);
        }
        myCurrentGame = myAvailableGames.get(index);
        setCurrentLevel(0);
    }

    @Override
    public void setCurrentGame(Game game) {
        if (!myAvailableGames.contains(game)) {
            throw new IllegalArgumentException("Selected game is not available");
        }
        setCurrentGame(myAvailableGames.indexOf(game));
    }

    public void removeGame(Game game) {
        if (!myAvailableGames.contains(game)) {
            throw new IllegalArgumentException("Selected game is not available");
        }
        myAvailableGames.remove(game);
        if (myAvailableGames.size() == 0) {
            myCurrentGame = null;
            updateObservers();
        } else {
            setCurrentGame(myAvailableGames.size() - 1);
        }
    }

    public List<Game> getAvailableGames() {
        return myAvailableGames;
    }

    @Override
    public Game getCurrentGame() {
        return myCurrentGame;
    }
    
    public String getResourceFilePath() {
    	return resourceLanguageFilePath;
    }
    
    public void setResourceFilePath(String filePath) {
    	resourceLanguageFilePath = filePath;
    }

    @Override
    public boolean addLevel(Level level) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException("No game currently selected");
        }
        if (myCurrentGame.addLevel(level)) {
        	setCurrentLevel(myCurrentGame.getAllLevelsReadOnly().size() - 1);
        	return true;
        }
        return false;
    }

    public void setCurrentLevel(int index) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException("No game currently selected");
        }
        if (index < 0 || index >= myCurrentGame.getAllLevelsReadOnly().size()) {
            throw new IllegalArgumentException("Level index out of range: " + index);
        }
        myCurrentLevel = myCurrentGame.getAllLevelsReadOnly().get(index);
        updateObservers();
    }

    public void setCurrentLevel(Level level) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException("No game currently selected");
        }
        if (!myCurrentGame.getAllLevelsReadOnly().contains(level)) {
            throw new IllegalArgumentException("Selected level is not available");
        }
        setCurrentLevel(myCurrentGame.getAllLevelsReadOnly().indexOf(level));
    }

    public Level getCurrentLevel() {
        return myCurrentLevel;
    }

    public void removeLevel(Level level) {
        if (!myCurrentGame.getAllLevelsReadOnly().contains(level)) {
            throw new IllegalArgumentException("Selected level is not available");
        }
        myCurrentGame.removeLevel(level);
        if (myCurrentGame.getAllLevelsReadOnly().size() == 0) {
            myCurrentLevel = null;
            updateObservers();
        } else {
            setCurrentLevel(myCurrentGame.getAllLevelsReadOnly().size() - 1);
        }
    }

    @Override
    public void load() {
        // TODO: 12/5/16 method does nothing
    }

    @Override
    public void setLanguage(String lang) {
        // TODO: 12/5/16 method does nothing
    }

    private void updateObservers() {
        setChanged();
        notifyObservers();
    }
}
