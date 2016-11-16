package game_object.simulation;

import java.util.ArrayList;

import game_object.core.ImageStyle;

public interface IBodyWithImage {

	ArrayList<String> getImgPaths();

	void setImgPaths(ArrayList<String> imgPaths);

	ImageStyle getImgStyle();

	void setImgStyle(ImageStyle imgStyle);
	
}
