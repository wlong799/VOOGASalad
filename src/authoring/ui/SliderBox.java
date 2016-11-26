package authoring.ui;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author billyu
 * a box that contains a parameter slider and a text prompt
 */
public class SliderBox {
	private Slider slider;
	private Text hud;
	private VBox box;
	
	public SliderBox(String text, int min, int max, int value, int increment){
		hud = new Text(text);
		slider = new ParamSlider(min, max, value, increment).getSlider();
	}
	
	public SliderBox(String text, int min, int max, int value, int increment, 
			ChangeListener<Number> listener) {
		hud = new Text(text);
		slider = new ParamSlider(min, max, value, increment).getSlider();
		slider.valueProperty().addListener(listener);
	}

	/**
	 * @return the frontend box
	 */
	public VBox getBox() {
		box = new VBox();
		box.getChildren().addAll(hud, slider);
		return box;
	}
}
