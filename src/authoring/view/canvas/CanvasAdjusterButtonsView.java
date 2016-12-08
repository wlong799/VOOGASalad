package authoring.view.canvas;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.constants.ResourceBundles;

public class CanvasAdjusterButtonsView extends AbstractView {
	
	private Button screenWider;
	private Button screenNarrower;
	private Button screenTaller;
	private Button screenShorter;
	private ResourceBundle canvasProperties;

	public CanvasAdjusterButtonsView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void initUI() {
		canvasProperties = ResourceBundles.canvasProperties;
		
		screenWider = new Button();
		screenNarrower = new Button();
		screenTaller = new Button();
		screenShorter = new Button();
		
		setButton(screenWider, 0, "img/wider.png");
		setButton(screenNarrower, 1, "img/thinner.png");
		setButton(screenTaller, 2, "img/taller.png");
		setButton(screenShorter, 3, "img/shorter.png");
		
		this.addUIAll(screenWider, screenNarrower, screenTaller, screenShorter);
		screenAdjusterButtonInit();
	}
	
	private void setButton(Button button, int multiplier, String path) {
		button.setPrefWidth(Double.parseDouble(canvasProperties.getString("BUTTON_WIDTH")));
		button.setLayoutX(Double.parseDouble(canvasProperties.getString("BUTTON_WIDTH"))*multiplier);
		Image image = new Image(path);
		button.setGraphic(new ImageView(image));
		button.setPrefHeight(Double.parseDouble(canvasProperties.getString("BUTTON_HEIGHT")));
	}

	@Override
	protected void updateLayoutSelf() {
	}
	
	private void screenAdjusterButtonInit(){
		screenNarrower.setOnMouseClicked((event) -> {
			this.getController().getCanvasViewController().shrink();
		});
		screenWider.setOnAction((event) -> {
			this.getController().getCanvasViewController().expand();
		});
		screenTaller.setOnAction((event) -> {
			this.getController().getCanvasViewController().taller();
		});
		screenShorter.setOnAction((event) -> {
			this.getController().getCanvasViewController().shorter();
		});
	}

}
