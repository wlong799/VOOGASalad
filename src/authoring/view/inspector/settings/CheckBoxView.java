package authoring.view.inspector.settings;

import authoring.AuthoringController;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;

public class CheckBoxView extends AbstractSettingsView {
	private String myTitle;
	private boolean isSelected;
	private ChangeListener<Boolean> myChangeListener;

	private CheckBox mySnapBox;

	public CheckBoxView(AuthoringController controller, String title, boolean selected,
			ChangeListener<Boolean> changeListener) {
		super(controller);
		myTitle = title;
		isSelected = selected;
		myChangeListener = changeListener;
	}

	@Override
	public void initializeSettings() {
		myLabel.setText(myTitle);
		mySnapBox.selectedProperty().addListener(myChangeListener);
		mySnapBox.setSelected(isSelected);
	}

	@Override
	protected void initUI() {
		super.initUI();
		mySnapBox = new CheckBox();
		myContent.getChildren().add(mySnapBox);
	}

	@Override
	protected void updateLayoutSelf() {
		super.updateLayoutSelf();
		mySnapBox.setPrefWidth(this.getWidth());
	}
	
	
	public CheckBox getBox(){
	    return mySnapBox;
	}
	

}
