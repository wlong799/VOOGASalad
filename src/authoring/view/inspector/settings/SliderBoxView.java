package authoring.view.inspector.settings;

import authoring.AuthoringController;
import authoring.constants.UIConstants;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Settings box that contains a slider and a text prompt.
 *
 * @author billyu, Will Long
 */
public class SliderBoxView extends AbstractSettingsView {
    private String myTitle;
    private double myMin, myMax, myValue, myIncrement;
    private ChangeListener<Number> myListener;

    private Slider mySlider;

    public SliderBoxView(AuthoringController controller, String title, double min, double max, double value,
                         double increment, ChangeListener<Number> listener) {
        super(controller);
        myTitle = title;
        myMin = min;
        myMax = max;
        myValue = value;
        myIncrement = increment;
        myListener = listener;
    }

    public void initUI() {
        super.initUI();
        myContent.setFocusTraversable(true);
        mySlider = new Slider();
        mySlider.setShowTickLabels(true);
        mySlider.setShowTickMarks(true);
        myContent.getChildren().add(mySlider);
    }

    public void setValue(double value) {
        mySlider.setValue(value);
    }

    @Override
    public void initializeSettings() {
        myLabel.setText(myTitle);
        mySlider.setMin(myMin);
        mySlider.setMax(myMax);
        mySlider.setValue(myValue);
        mySlider.setMajorTickUnit((myMax - myMin) / UIConstants.MAJOR_TICK_DIVISOR);
        mySlider.setMinorTickCount((int) ((myMax - myMin) / UIConstants.MINOR_TICK_DIVISOR));
        mySlider.setBlockIncrement(myIncrement);
        if (myListener != null) {
            mySlider.valueProperty().addListener(myListener);
        }
    }

    protected void updateLayoutSelf() {
        super.updateLayoutSelf();
        mySlider.setPrefWidth(getWidth());
    }
}
