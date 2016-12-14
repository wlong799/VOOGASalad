package game_engine.enemyai;

/**
 * Interafce used by the game engine to create a Enemy Controller
 * 
 * @author Charlie Wang
 *
 */
public interface IEnemyControllerFactory {

	/**
	 * @param type
	 *            Enemy difficulty
	 * @return an enemy controller according to the type given by the factory
	 *         caller (engine)
	 */
	public IEnemyController createEnemyController(EnemyLevelTypes type);

}
