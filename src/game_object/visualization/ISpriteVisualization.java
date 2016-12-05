package game_object.visualization;

import game_object.core.ImageStyle;

/**
 * A front-end representation of a sprite.
 * Note that this might be very inefficient.
 * It'd be better to give each sprite an ID, and each time GUI just changes the
 * location of the imageview instead of creating a new one from scratch with imagepath
 * and x, y, height width.
 * But let's save it for later once we get a simple, working game.
 * TODO: what I just said.
 * @author Jay
 *
 */
public interface ISpriteVisualization {
	
	String getImagePath();
	
	double getXForVisualization();
	
	double getYForVisualization();
	
	double getWidthForVisualization();
	
	double getHeightForVisualization();
	
	ImageStyle getImageStyle();
	
}
