package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import game_object.core.Game;
import game_object.level.Level;

/**
 * AuthorEnvironment contains all of the games currently available for editing. It extends Observable so that
 * elements within the authoring environment know when they need to triggerUpdate.
 */
public class AuthorEnvironment extends Observable implements IAuthorEnvironment {
    private List<Game> myAvailableGames;
    private Game myCurrentGame;
    private Level myCurrentLevel;
    private ResourceBundle  myLanguageResourceBundle; 

    public AuthorEnvironment() {
        myAvailableGames = new ArrayList<>();
    }
    
    public void initLanguageResource(String filePath) {
    	myLanguageResourceBundle = ResourceBundle.getBundle(filePath);
    }

    @Override
    public void addGame(Game game) {
        myAvailableGames.add(game);
        setCurrentGame(game);
    }

    @Override
    public void setCurrentGame(int index) {
        if (index < 0 || index >= myAvailableGames.size()) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("gameIndex") + index);
        }
        myCurrentGame = myAvailableGames.get(index);
        setCurrentLevel(0);
    }

    @Override
    public void setCurrentGame(Game game) {
        if (!myAvailableGames.contains(game)) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("notAvailable"));
        }
        setCurrentGame(myAvailableGames.indexOf(game));
    }

    public void removeGame(Game game) {
        if (!myAvailableGames.contains(game)) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("notAvailable"));
        }
        myAvailableGames.remove(game);
        if (myAvailableGames.size() == 0) {
            myCurrentGame = null;
            triggerUpdate();
        } else {
            setCurrentGame(myAvailableGames.size() - 1);
        }
    }
    
    public ResourceBundle getLanguageResourceBundle() {
    	return myLanguageResourceBundle;
    }

    public List<Game> getAvailableGames() {
        return myAvailableGames;
    }

    @Override
    public Game getCurrentGame() {
        return myCurrentGame;
    }

    @Override
    public boolean addLevel(Level level) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("noGame"));
        }
        if (myCurrentGame.addLevel(level)) {
        	setCurrentLevel(myCurrentGame.getAllLevelsReadOnly().size() - 1);
        	return true;
        }
        return false;
    }

    public void setCurrentLevel(int index) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("noGame"));
        }
        if (index < 0 || index >= myCurrentGame.getAllLevelsReadOnly().size()) {
            throw new IllegalArgumentException("Level index out of range: " + index);
        }
        myCurrentLevel = myCurrentGame.getAllLevelsReadOnly().get(index);
        triggerUpdate();
    }

    public void setCurrentLevel(Level level) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("noGame"));
        }
        if (!myCurrentGame.getAllLevelsReadOnly().contains(level)) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("outOfRange"));
        }
        setCurrentLevel(myCurrentGame.getAllLevelsReadOnly().indexOf(level));
    }

    public Level getCurrentLevel() {
        return myCurrentLevel;
    }

    public void removeLevel(Level level) {
        if (!myCurrentGame.getAllLevelsReadOnly().contains(level)) {
            throw new IllegalArgumentException(myLanguageResourceBundle.getString("levelNotAvailable"));
        }
        myCurrentGame.removeLevel(level);
        if (myCurrentGame.getAllLevelsReadOnly().size() == 0) {
            myCurrentLevel = null;
            triggerUpdate();
        } else {
            setCurrentLevel(myCurrentGame.getAllLevelsReadOnly().size() - 1);
        }
    }

    public void triggerUpdate() {
        setChanged();
        notifyObservers();
    }
}
