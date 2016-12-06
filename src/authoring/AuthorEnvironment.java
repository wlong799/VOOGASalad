package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import game_object.LevelGenerator;
import game_object.core.Game;
import game_object.level.Level;

/**
 * AuthorEnvironment contains all of the games currently available for editing. It extends Observable so that
 * elements within the authoring environment know when they need to update.
 */
public class AuthorEnvironment extends Observable implements IAuthorEnvironment {
    private List<Game> myAvailableGames;
    private Game myCurrentGame;
    private Level myCurrentLevel;

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
            update();
        } else {
            setCurrentGame(myAvailableGames.size() - 1);
        }
    }

    @Override
    public Game getCurrentGame() {
        return myCurrentGame;
    }

    @Override
    public void addLevel(Level level) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException("No game currently selected");
        }
        myCurrentGame.addLevel(level);
        setCurrentLevel(myCurrentGame.getAllLevelsReadOnly().size() - 1);
    }

    public void setCurrentLevel(int index) {
        if (myCurrentGame == null) {
            throw new IllegalArgumentException("No game currently selected");
        }
        if (index < 0 || index >= myCurrentGame.getAllLevelsReadOnly().size()) {
            throw new IllegalArgumentException("Level index out of range: " + index);
        }
        myCurrentLevel = myCurrentGame.getAllLevelsReadOnly().get(index);
        update();
    }

    public Level getCurrentLevel() {
        return myCurrentLevel;
    }

    public void removeLevel(Level level) {
        if (!myAvailableGames.contains(level)) {
            throw new IllegalArgumentException("Selected level is not available");
        }
        myCurrentGame.removeLevel(level);
        if (myCurrentGame.getAllLevelsReadOnly().size() == 0) {
            myCurrentLevel = null;
            update();
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

    private void update() {
        setChanged();
        notifyObservers();
    }
}
