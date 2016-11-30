package authoring;

import java.util.ArrayList;
import java.util.List;

import game_object.LevelGenerator;
import game_object.core.Game;
import game_object.level.Level;
import javafx.beans.property.SimpleIntegerProperty;

public class AuthorEnvironment implements IAuthorEnvironment {

    private List<Game> games;
    private Game currentGame;
    private Level currentLevel;
    private SimpleIntegerProperty numLevels, currentLevelIndex, numGames, currentGameIndex;

    public AuthorEnvironment() {
        games = new ArrayList<>();
        numLevels = new SimpleIntegerProperty();
        currentLevelIndex = new SimpleIntegerProperty();
        numGames = new SimpleIntegerProperty();
        currentGameIndex = new SimpleIntegerProperty();
    }

    @Override
    public void addGame(Game game) {
        games.add(game);
        setCurrentGame(games.size() - 1);
    }

    @Override
    public void setCurrentGame(int index) {
        numGames.set(games.size());
        if (index < 0 || index >= games.size()) {
            throw new IllegalArgumentException("index for level out of range");
        }
        currentGameIndex.set(index);
        currentGame = games.get(index);
        setCurrentLevel(0);
    }

    @Override
    public void setCurrentGame(Game game) {
        if (!games.contains(game)) {
            addGame(game);
        } else {
            setCurrentGame(games.indexOf(game));
        }
    }

    public void closeCurrentGame() {
        games.remove(getCurrentGame());
        if (games.size() == 0) {
            System.exit(0);
        }
        setCurrentGame(games.size() - 1);
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
        setCurrentLevel(currentGame.getAllLevelsReadOnly().size() - 1);
    }

    @Override
    public void setCurrentLevel(int index) {
        if (currentGame == null) {
            throw new IllegalArgumentException("no current game");
        }
        numLevels.set(currentGame.getAllLevelsReadOnly().size());
        if (index < 0 || index >= currentGame.getAllLevelsReadOnly().size()) {
            throw new IllegalArgumentException("index for level out of range");
        }
        currentLevelIndex.set(index);
        currentLevel = currentGame.getAllLevelsReadOnly().get(index);
    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void deleteCurrentLevel() {
        Level level = getCurrentLevel();
        getCurrentGame().removeLevel(level);
        if (getCurrentGame().getAllLevelsReadOnly().size() == 0) {
            addLevel(LevelGenerator.getTestLevelB());
        }
        setCurrentLevel(getCurrentGame().getAllLevelsReadOnly().size() - 1);
    }

    public SimpleIntegerProperty getNumLevels() {
        return numLevels;
    }

    public SimpleIntegerProperty getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public SimpleIntegerProperty getNumGames() {
        return numGames;
    }

    public SimpleIntegerProperty getCurrentGameIndex() {
        return currentGameIndex;
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