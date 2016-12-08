package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.level.Level;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class LevelSettingsView extends AbstractView {
	
	private InspectorLevelView myConfiguringView;
	private EditSettingsView myEditingView;
	private VBox myBox;
	private Level myLevel;

	public LevelSettingsView(AuthoringController controller) {
		super(controller);
	}
	
	public void setLevel(Level level) {
		myLevel = level;
		this.updateUI();
	}
	
	@Override
	public Parent getUI() {
		return myBox;
	}

	@Override
	protected void initUI() {
		myBox = new VBox();
		myConfiguringView = new InspectorLevelView(this.getController());
		myEditingView = new EditSettingsView(this.getController());
		this.addSubViews(myConfiguringView, myEditingView);
		myBox.getChildren().addAll(myConfiguringView.getUI(), myEditingView.getUI());
	}

	@Override
	protected void updateLayoutSelf() {
	}
	
	private void updateUI() {
		myConfiguringView.setLevel(myLevel);
	}

}
