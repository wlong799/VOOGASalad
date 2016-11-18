package game_engine.inputcontroller;

import java.util.List;

import game_object.acting.KeyEvent;

public class InputController implements IInputController {

	private List<KeyEvent> myList;

	public InputController() {

	}

	@Override
	public void setInputList(List<KeyEvent> list) {
		myList = list;
	}

	@Override
	public void executeInput() {
		KeyEvent event;
		if (myList == null || myList.size() == 0) {
			return;
		}
		event = myList.get(0);
		
	}

}
