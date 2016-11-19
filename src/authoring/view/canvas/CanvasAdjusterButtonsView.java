package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.View;
import authoring.constants.UIConstants;
import javafx.scene.control.Button;

public class CanvasAdjusterButtonsView extends View {
	
	private Button screenWider;
	private Button screenNarrower;

	public CanvasAdjusterButtonsView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void initUI() {
		screenWider = new Button(" < > ");
		screenNarrower = new Button(" > < ");
		
		screenWider.setLayoutX(UIConstants.BUTTON_WIDTH);
		screenNarrower.setPrefWidth(UIConstants.BUTTON_WIDTH);
		screenWider.setPrefWidth(UIConstants.BUTTON_WIDTH);
		
		this.addUIAll(screenWider, screenNarrower);
		screenAdjusterButtonInit();
	}

	@Override
	protected void layoutSelf() {
	}
	
	private void screenAdjusterButtonInit(){
		screenNarrower.setOnAction((event) -> {
			this.getController().getCanvasViewController().shrink();
		});
		screenWider.setOnAction((event) -> {
			this.getController().getCanvasViewController().expand();
		});
	}

}
