package game_object.core;

import java.io.Serializable;

public class Dimension implements Serializable {

	private double myWidth;
	private double myHeight;
	
	public Dimension(double width, double height) {
		myWidth = width;
		myHeight = height;
	}
	
	public double getWidth() {
		return myWidth;
	}
	
	public void setWidth(double width) {
		myWidth = width;
	}
	
	public double getHeight() {
		return myHeight;
	}
	
	public void setHeight(double height) {
		myHeight = height;
	}
	
}
