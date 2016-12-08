package authoring.ui;

import java.util.ResourceBundle;

import javafx.scene.control.Slider;
import resources.ResourceBundles;

/**
 * @author billyu
 * Slider for parameter control
 * getSlider will return the slider
 */
public class ParamSlider {
	
	private double min, max, value, increment;
	private ResourceBundle inspectorProperties;
	
	public ParamSlider (double min, double max, double value, double increment) {
		inspectorProperties = ResourceBundles.inspectorProperties;
		
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
		slider.setMajorTickUnit((max - min) / Double.parseDouble(inspectorProperties.getString("MAJOR_TICK_DIVISOR")));
		slider.setMinorTickCount((int) ((max - min) / Double.parseDouble(inspectorProperties.getString("MINOR_TICK_DIVISOR"))));
		slider.setBlockIncrement(increment);
		return slider;
	}
}
