package game_engine.inputcontroller;

import java.util.List;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.KeyEvent;
import game_object.character.ICharacter;
import game_object.character.IMover;
import game_object.core.ISprite;
import game_object.level.Level;

public class InputController implements IInputController {

	private Set<KeyEvent> myList;
	private boolean jumping;
	private Level myLevel;
	private boolean exist;

	public InputController(Level level) {
		myLevel = level;
	}

	@Override
	public void setInputList(Set<KeyEvent> list) {
		myList = list;
	}

	@Override
	public void executeInput() {
		exist = false;
		if (myList == null || myList.size() == 0) {
			return;
		}
		for (KeyEvent event : myList) {
			List<ActionTrigger> trigger = myLevel.getTriggersWithEvent(event);
			for (ActionTrigger actionTrigger : trigger) {
				chooseAction(actionTrigger);
			}
		}
		//boolean myJump = jumping
		// System.out.println(exist);
	}

	private void chooseAction(ActionTrigger at) {
		ISprite sprite = at.getSprite();
		if (at.getActionName() == ActionName.MOVE_LEFT) {
			IMover m = (IMover) sprite;
			m.moveLeft();
			exist = true;
		} else if (at.getActionName() == ActionName.MOVE_RIGHT) {
			IMover m = (IMover) sprite;
			m.moveRight();
			exist = true;
		} else if (at.getActionName() == ActionName.JUMP) { 
			IMover m = (IMover) sprite;
			m.jumpUp();
			jumping = true;
		} else if (at.getActionName() == ActionName.SHOOT) {
			ICharacter c = (ICharacter) sprite;
			c.shoot();
		}
	}

	public boolean getInputExist() {
		return exist;
	}
}
