package game_object.simulation;

import game_object.core.Dimension;

public interface IBodyWithDimension {

	void setDimension(Dimension dimension);
	
	Dimension getDimension();
}
