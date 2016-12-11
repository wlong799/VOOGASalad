package game_engine.enemyai;

/**
 * Interafce used by the game engine to create a Enemy Controller
 * 
 * @author Charlie Wang
 *
 */
public interface IEnemyControllerFactory {
	
	public IEnemyController createEnemyController(EnemyLevelTypes type);
	
}
