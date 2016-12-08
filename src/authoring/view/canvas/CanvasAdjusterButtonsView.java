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
		
		setButton(screenWider, Integer.parseInt(canvasProperties.getString("SCREEN_WIDER_POSITION")), "img/wider.png");
		setButton(screenNarrower, Integer.parseInt(canvasProperties.getString("SCREEN_NARROWER_POSITION")), "img/thinner.png");
		setButton(screenTaller, Integer.parseInt(canvasProperties.getString("SCREEN_TALLER_POSITION")), "img/taller.png");
		setButton(screenShorter, Integer.parseInt(canvasProperties.getString("SCREEN_SHORTER_POSITION")), "img/shorter.png");
		
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
