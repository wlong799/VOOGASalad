package authoring.view.inspector.settings;

import authoring.AuthoringController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class physicsConfiguringView extends AbstractSettingsView {

	private ComboBox physicsSettings;
	
	public physicsConfiguringView(AuthoringController controller) {
		super(controller);
	}

	@Override
	public void initializeSettings() {
		ObservableList<String> physicsOptions = FXCollections.observableArrayList(
				"option"
				);
		
		physicsSettings = new ComboBox(physicsOptions);
		
		// game engine - three class that implement IPhysics strategy
		// be able to choose between those classes for every sprite
		// set physsics strategy
	}

}
