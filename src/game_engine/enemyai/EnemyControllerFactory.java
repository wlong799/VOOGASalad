package game_engine.enemyai;

public class EnemyControllerFactory implements IEnemyControllerFactory{

	@Override
	public IEnemyController createEnemyController(EnemyLevelTypes type) {
		if (type == EnemyLevelTypes.EASY)
		{}
		return null;
	}
	
}
