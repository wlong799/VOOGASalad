package game_engine.enemyai;

import java.util.Set;

import game_object.acting.ActionName;
import game_object.character.IMover;
import game_object.core.Game;

public class EasyEnemyController extends AbstractEnemyController{

	public EasyEnemyController() {
		super();
	}

	@Override
	public Set<ActionName> getActions(IMover enemy) {
		return null;
	}

}
