package authoring.view.inspector;

import game_object.acting.ActionName;
import game_object.acting.KeyEvent;
import game_object.core.ISprite;
import javafx.scene.input.KeyCode;

public class ActionTypeKeyInput {
	
	private ActionName actionName;
	private KeyEvent keyEvent;
	private KeyCode keyInput;
	private ISprite sprite;
	
	/**
	 * constructor for when no key input has been set
	 * @param actionNameToSet
	 */
	public ActionTypeKeyInput(ActionName actionNameToSet, ISprite spriteToSet) {
		this.actionName = actionNameToSet;
		this.keyInput = null;
		this.sprite = spriteToSet;
		
		setActionTrigger();
	}
	
	public void updateActionTypeKeyInput(KeyCode key) {
		this.keyInput = key;
	}
	
	public String getActionName() {
		return this.actionName.toString();
	}
	
	private void setActionTrigger() {
		//
	}
}

