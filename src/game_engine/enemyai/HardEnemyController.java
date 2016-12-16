package game_engine.enemyai;

import java.util.HashSet;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.ICharacter;
import game_object.character.IMover;

/**
 * @author Charlie Wang
 *
 */
public class HardEnemyController extends AbstractEnemyController{

	private static final double JUMPPROB = 0.01;
	private static final double FOLLOWHEROPROB = 0.99;
	private static final double SHOOTPROB = 0.02;
	private static final int RANDOMCAP = 10000;
	
	public HardEnemyController() {
		super();
	}

	@Override
	public Set<ActionName> getActions(IMover enemy, IMover hero) {
		Set<ActionName> set = new HashSet<ActionName>();
		
		//whether to jump
		double jumpProb = myRandom.nextInt(RANDOMCAP);
		if (jumpProb < RANDOMCAP * JUMPPROB) {
			set.add(ActionName.JUMP);
		}
		ICharacter newEnemy = (ICharacter) enemy;
		ICharacter newHero = (ICharacter) hero;
		
		//whether to move 
		double changeDirectionProb = myRandom.nextInt(RANDOMCAP);
		if (newHero.getPosition().getX() < newEnemy.getPosition().getX()) {
			if (changeDirectionProb < RANDOMCAP * FOLLOWHEROPROB) {
				set.add(ActionName.MOVE_LEFT);
			} else {
				set.add(ActionName.MOVE_RIGHT);
			}
		} else {
			if (changeDirectionProb < RANDOMCAP * FOLLOWHEROPROB) {
				set.add(ActionName.MOVE_RIGHT);
			} else {
				set.add(ActionName.MOVE_LEFT);
			}
		}
		
		//whether to shoot
		double shootProb = myRandom.nextInt(RANDOMCAP);
		if (shootProb < RANDOMCAP * SHOOTPROB) {
			set.add(ActionName.SHOOT);
		}
		
		return set;
	}

}
