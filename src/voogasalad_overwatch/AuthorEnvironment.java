package voogasalad_overwatch;

import java.util.Collection;

public class AuthorEnvironment implements IAuthorEnvironment {
	
	private Collection<IGame> games;
	private IGame currentGame;
	private ILevel currentLevel;
	private IPhysicsEngine physicsEngine;

    @Override
    public void addGame(IGame game) {
        games.add(game);
    }

    @Override
    public void setCurrentGame(IGame game) {
        currentGame = game;
    }

    @Override
    public IGame getCurrentGame() {
        return currentGame;
    }

    @Override
    public void addLevel(ILevel level) {
        // TODO current IGame interface does not support this
    	// want something like currentGame.addLevel(level);
    }

    @Override
    public void setCurrentLevel(ILevel level) {
        currentLevel = level;
    }

    @Override
    public ILevel getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void setPhysicsEngine(IPhysicsEngine physicsEngine) {
        this.physicsEngine = physicsEngine;
    }

    @Override
    public IPhysicsEngine getPhysicsEngine() {
        return physicsEngine;
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
