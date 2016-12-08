package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class EditSettingsView extends AbstractView {
	
	private VBox myBox;
	private CheckBox mySnapBox;

	public EditSettingsView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	public Parent getUI() {
		return myBox;
	}

	@Override
	protected void initUI() {
		myBox = new VBox();
		mySnapBox = new CheckBox("Snap to Grid");
		mySnapBox.selectedProperty().addListener((obv, old_val, new_val) -> {
			this.getController().getCanvasViewController().setSnapToGrid(new_val);
		});
		mySnapBox.setSelected(false);
		myBox.getChildren().add(mySnapBox);
	}

	@Override
	protected void updateLayoutSelf() {
	}

}
