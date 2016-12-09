package game_engine.inputcontroller;

import java.util.Set;

import game_object.acting.Event;
import game_object.acting.KeyEvent;

public class PseudoInputController implements IInputController{

	private Set<Event> myList;
	
	@Override
	public void setInputList(Set<Event> list) {
		myList = list;
	}
	
	@Override
	public void executeInput() {
		
	}

	@Override
	public boolean getInputExist() {
		return false;
	}

	@Override
	public void setCurrentTime(double time) {
		
	}

}
