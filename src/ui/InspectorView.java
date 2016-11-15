package ui;

import javafx.scene.layout.VBox;

public class InspectorView extends View implements Subscriber {
	
	private SpriteView inspectedSpriteView;
	private VBox configs;

	public InspectorView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	public void didUpdate(Publisher target) {
		if (target instanceof AuthoringController) {
			inspectedSpriteView = ((AuthoringController) target).getSelectedSpriteView();
		}
		updateUI();
	}
	
	@Override
	protected void initUI() {
		configs = new VBox();
	}

	@Override
	protected void layoutSelf() {
		// TODO
	}
	
	private void updateUI() {
		configs.getChildren().clear();
		// TODO fill in VBox the selections and update SpriteView
	}

}
