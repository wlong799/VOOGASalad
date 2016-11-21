package game_object.acting;

import javafx.scene.input.KeyCode;

public class KeyEvent extends Event {

	KeyCode myKey;
	
	public KeyEvent(KeyCode key) {
		super("KEY");
		myKey = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((myKey == null) ? 0 : myKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyEvent other = (KeyEvent) obj;
		if (myKey == null) {
			if (other.myKey != null)
				return false;
		} else if (!myKey.equals(other.myKey))
			return false;
		return true;
	}
	


}
