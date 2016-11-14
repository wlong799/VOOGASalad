package game_object.acting;

public class Event {

	private String myEventType;
	
	public Event(String t) {
		myEventType = t;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myEventType == null) ? 0 : myEventType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (myEventType == null) {
			if (other.myEventType != null)
				return false;
		} else if (!myEventType.equals(other.myEventType))
			return false;
		return true;
	}
	
}
