package game_object.simulation;

import java.util.List;

import game_object.core.Dimension;
import game_object.core.ImageStyle;

public interface IBodyWithImage {

	List<String> getImagePaths();

	void setImagePaths(List<String> imagePaths);

	ImageStyle getImageStyle();

	void setImageStyle(ImageStyle imageStyle);
	
	void setDimension(Dimension dimension);
	
	Dimension getDimension();
}
