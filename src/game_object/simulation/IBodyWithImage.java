package game_object.simulation;

import java.util.List;

import game_object.core.ImageStyle;

public interface IBodyWithImage {

	List<String> getImgPaths();

	void setImgPaths(List<String> imgPaths);

	ImageStyle getImgStyle();

	void setImgStyle(ImageStyle imgStyle);
	
}
