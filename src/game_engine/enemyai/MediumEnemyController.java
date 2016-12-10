package game_engine.enemyai;

import java.util.HashSet;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.IMover;

public class MediumEnemyController extends AbstractEnemyController {

	private static final double JUMPPROB = 0.01;
	private static final double SHOOTPROB = 0.02;
	private static final double CHANGEDIRECTIONPROB = 0.01;
	private static final int RANDOMCAP = 10000;
	
	public MediumEnemyController() {
		super();
	}

	@Override
	public Set<ActionName> getActions(IMover enemy, IMover Hero) {
		Set<ActionName> set = new HashSet<ActionName>();
		
		//whether to jump
		double jumpProb = myRandom.nextInt(RANDOMCAP);
		if (jumpProb < RANDOMCAP * JUMPPROB) {
			set.add(ActionName.JUMP);
		}
		
		//whether to move
		double changeDirectionProb = myRandom.nextInt(RANDOMCAP);
		if (changeDirectionProb < RANDOMCAP * CHANGEDIRECTIONPROB) {
			set.add(enemy.isFacingLeft()?ActionName.MOVE_RIGHT:ActionName.MOVE_LEFT); 
		} else {
			set.add(enemy.isFacingLeft()?ActionName.MOVE_LEFT:ActionName.MOVE_RIGHT); 
		}
		
		//whether to shoot
		double shootProb = myRandom.nextInt(RANDOMCAP);
		if (shootProb < RANDOMCAP * SHOOTPROB) {
			set.add(ActionName.SHOOT);
		}
		
		return set;
	}

}
