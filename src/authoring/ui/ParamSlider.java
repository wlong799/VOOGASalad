package authoring.ui;

import javafx.scene.control.Slider;

/**
 * @author billyu
 * Slider for parameter control
 * getSlider will return the slider
 */
public class ParamSlider {
	
	private int min, max, value, increment;
	
	public ParamSlider (int min, int max, int value, int increment) {
		this.min = min;
		this.max = max;
		this.value = value;
		this.increment = increment;
	}
	
	public Slider getSlider() {
		Slider slider = new Slider();
		slider.setMin(min);
		slider.setMax(max);
		slider.setValue(value);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(increment);
		return slider;
	}
}
