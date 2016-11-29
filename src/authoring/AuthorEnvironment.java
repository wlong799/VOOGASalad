package authoring;

import java.util.ArrayList;
import java.util.List;

import game_object.LevelGenerator;
import game_object.framework.Game;
import game_object.level.Level;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;

public class AuthorEnvironment implements IAuthorEnvironment {

    private List<Game> games;
    private Game currentGame;
    private Level currentLevel;
    private SimpleIntegerProperty numLevels, currentLevelIndex;

    public AuthorEnvironment() {
        games = new ArrayList<>();
        numLevels = new SimpleIntegerProperty();
        currentLevelIndex = new SimpleIntegerProperty();
        numLevels.set(0);
        currentLevelIndex.set(0);
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
        setCurrentLevel(0);
    }

    @Override
    public void setCurrentGame(Game game) {
        if (!games.contains(game)) {
            games.add(game);
        }
        currentGame = game;
        setCurrentLevel(0);
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
        setCurrentLevel(currentGame.getAllLevels().size() - 1);
    }

    @Override
    public void setCurrentLevel(int index) {
        numLevels.set(currentGame.getAllLevels().size());
        currentLevelIndex.set(index);
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

    public SimpleIntegerProperty getNumLevels() {
        return numLevels;
    }

    public SimpleIntegerProperty getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public void deleteCurrentLevel() {
        Level level = getCurrentLevel();
        getCurrentGame().removeLevel(level);
        if (getCurrentGame().getAllLevels().size() == 0) {
            addLevel(LevelGenerator.getTestLevelB());
        }
        setCurrentLevel(getCurrentGame().getAllLevels().size() - 1);
    }
}
