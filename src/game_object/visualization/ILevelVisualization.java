package game_object.visualization;

import java.util.List;

/**
 * A front-end representation of a level
 * @author Jay
 */
public interface ILevelVisualization {
	
	void init();
	
	List<ISpriteVisualization> getAllSpriteVisualizations();
	
}
