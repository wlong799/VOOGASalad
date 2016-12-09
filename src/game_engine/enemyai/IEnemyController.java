package game_engine.enemyai;

import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.IMover;

public interface IEnemyController {
	
	public Set<ActionName> getActions(IMover enemy);
	
	public void executeInput(IMover enemy, Set<ActionName> events);
	
}
