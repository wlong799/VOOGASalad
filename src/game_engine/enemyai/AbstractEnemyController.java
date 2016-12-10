package game_engine.enemyai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.ICharacter;
import game_object.character.IMover;
import game_object.core.Game;
import game_object.weapon.Projectile;

/**
 * abstract class for all enemy controller; contains the universal input executer
 * 
 * @author Charlie Wang
 *
 */
public abstract class AbstractEnemyController implements IEnemyController{
	
	protected Random myRandom;
	protected List<Projectile> myProjectiles;
	
	public AbstractEnemyController() {
		myRandom = new Random();
		myProjectiles = new ArrayList<Projectile>();
	}
	
	public void executeInput(IMover enemy, Set<ActionName> events) {
		for (ActionName e: events) {
			if (e == ActionName.JUMP) enemy.jumpUp();
			else if (e == ActionName.MOVE_LEFT) enemy.moveLeft();
			else if (e == ActionName.MOVE_RIGHT) enemy.moveRight();
			else if (e == ActionName.SHOOT) {
				ICharacter character = (ICharacter) enemy;
				//myGame.getCurrentLevel().getProjectiles().add(ProjectileManager.addProjectile(character));
				myProjectiles.add(ProjectileManager.addProjectile(character));
			}
		}
	}
	
	public List<Projectile> getNewProjectiles() {
		return myProjectiles;
	}
}
