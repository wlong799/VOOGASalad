package game_object.core;

/**
 * An enum class for image rendering style
 * @author Yilun
 *
 */
public enum ImageStyle {

	// use the actual image size
	TRUE_SIZE, 
	// stretch or shrink the image to fit the specified Dimension
	FIT, 
	// tile the image to fit the specified Dimension
	TILE;
}
