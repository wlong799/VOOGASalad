package game_engine.enemyai;

public class EnemyControllerFactory implements IEnemyControllerFactory{
	
	public EnemyControllerFactory() {
		
	}
	
	@Override
	public IEnemyController createEnemyController(EnemyLevelTypes type) {
		if (type == EnemyLevelTypes.EASY)
			return new EasyEnemyController();
		else if (type == EnemyLevelTypes.MEDIUM)
			return new MediumEnemyController();
		else if (type == EnemyLevelTypes.HARD)
			return new HardEnemyController();
		else 
			return null;
	}
	
}
