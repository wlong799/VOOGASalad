package game_engine.inputcontroller;

import java.util.List;

import game_object.acting.KeyEvent;

public interface IInputController {
	
	public abstract void setInputList(List<KeyEvent> list);
	
	public abstract void executeInput();

}
