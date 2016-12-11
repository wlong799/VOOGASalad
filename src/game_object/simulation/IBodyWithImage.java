package game_object.simulation;

import java.util.List;

import game_object.core.ImageStyle;

public interface IBodyWithImage extends IBodyWithDimension {

	List<String> getImagePaths();

	void setImagePaths(List<String> imagePaths);

	ImageStyle getImageStyle();

	void setImageStyle(ImageStyle imageStyle);

}
