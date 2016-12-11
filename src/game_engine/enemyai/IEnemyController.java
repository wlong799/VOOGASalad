package game_engine.enemyai;

import java.util.List;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.IMover;
import game_object.weapon.Projectile;

public interface IEnemyController {
	
	public Set<ActionName> getActions(IMover enemy, IMover hero);
	
	public void executeInput(IMover enemy, Set<ActionName> events);
	
	public List<Projectile> getNewProjectiles();
}
