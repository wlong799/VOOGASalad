package game_engine.enemyai;

import java.util.List;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.IMover;
import game_object.weapon.Projectile;

/**
 * Interface for different levels of enemy controller
 * 
 * @author Charlie Wang
 *
 */
public interface IEnemyController {

	/**
	 * @param enemy
	 *            The enemy to operate with
	 * @param hero
	 *            The hero to possibly follow
	 * @return a set of pseudo-actions to take
	 */
	public Set<ActionName> getActions(IMover enemy, IMover hero);

	/**
	 * Operate the set of actions like it is a player
	 * 
	 * @param enemy
	 *            The enemy to operate with
	 * @param events
	 *            The set of action to take
	 */
	public void executeInput(IMover enemy, Set<ActionName> events);

	/**
	 * @return A list of newly-generated projectiles
	 */
	public List<Projectile> getNewProjectiles();
}
