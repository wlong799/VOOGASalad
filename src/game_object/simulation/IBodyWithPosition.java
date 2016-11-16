package game_object.simulation;

import game_object.core.Position;

public interface IBodyWithPosition {
	
	Position getPosition();
	
	void setPosition(Position position);

}
