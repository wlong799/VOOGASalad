package game_engine.enemyai;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.ICharacter;
import game_object.character.IMover;
import game_object.core.Game;

public class EnemyController implements IEnemyController {

	private static final double JUMPPROB = 0.01;
	private static final double SHOOTPROB = 0.02;
	private static final double CHANGEDIRECTIONPROB = 0.01;
	private static final int RANDOMCAP = 10000;
	private Random myRandom;
	private Game myGame;

	public EnemyController(Game game) {
		myRandom = new Random();
		myGame = game;
	}

	@Override
	public Set<ActionName> getActions(IMover enemy) {
		Set<ActionName> set = new HashSet<ActionName>();
		
		double jumpProb = myRandom.nextInt(RANDOMCAP);
		if (jumpProb < RANDOMCAP * JUMPPROB) {
			set.add(ActionName.JUMP);
		}
		double jumpProb2 = myRandom.nextInt(RANDOMCAP);
		if (jumpProb2 < RANDOMCAP * CHANGEDIRECTIONPROB) {
			set.add(enemy.isFacingLeft()?ActionName.MOVE_RIGHT:ActionName.MOVE_LEFT); 
		} else {
			set.add(enemy.isFacingLeft()?ActionName.MOVE_LEFT:ActionName.MOVE_RIGHT); 
		}
		double jumpProb3 = myRandom.nextInt(RANDOMCAP);
		if (jumpProb3 < RANDOMCAP * SHOOTPROB) {
			set.add(ActionName.SHOOT);
		}
		return set;
	}

	public void executeInput(IMover enemy, Set<ActionName> events) {
		for (ActionName e: events) {
			if (e == ActionName.JUMP) enemy.jumpUp();
			else if (e == ActionName.MOVE_LEFT) enemy.moveLeft();
			else if (e == ActionName.MOVE_RIGHT) enemy.moveRight();
			else if (e == ActionName.SHOOT) {
				ICharacter character = (ICharacter) enemy;
				myGame.getCurrentLevel().getProjectiles().add(ProjectileManager.addProjectile(character));
			}
		}
	}

}
