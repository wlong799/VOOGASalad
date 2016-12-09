package authoring.ui;

import authoring.constants.UIConstants;
import javafx.scene.control.Slider;

/**
 * @author billyu
 * Slider for parameter control
 * getSlider will return the slider
 */
public class ParamSlider {
	
	private double min, max, value, increment;
	
	public ParamSlider (double min, double max, double value, double increment) {
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
		slider.setMajorTickUnit((max - min) / UIConstants.MAJOR_TICK_DIVISOR);
		slider.setMinorTickCount((int) ((max - min) / UIConstants.MINOR_TICK_DIVISOR));
		slider.setBlockIncrement(increment);
		return slider;
	}
}
