package game_engine.inputcontroller;

import java.util.List;
import java.util.Set;

import game_object.acting.KeyEvent;

public interface IInputController {
	
	public abstract void setInputList(Set<KeyEvent> list);
	
	public abstract void executeInput();

}
