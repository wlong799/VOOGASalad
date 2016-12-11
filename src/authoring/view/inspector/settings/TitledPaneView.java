package authoring.view.inspector.settings;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import game_object.level.Level;
import goal.time.PassTime;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import resources.ResourceBundles;

public class TitledPaneView extends AbstractSettingsView {

	private TitledPane myTimeGoalTitledPane;
	private Level myLevel;
	private ResourceBundle myGoalPromptProperties;	
	private ResourceBundle myLanguageResourceBundle;
	
	public TitledPaneView(AuthoringController controller, Level level) {
		super(controller);
		
		myGoalPromptProperties = ResourceBundles.goalPromptProperties;
	}
	
	@Override
    protected void initUI() {
        super.initUI();
        myLanguageResourceBundle = super.getController().getEnvironment().getLanguageResourceBundle();
    }

	@Override
	public void initializeSettings() {
		myLevel = getController().getEnvironment().getCurrentLevel();
		initializeTimeGoalTitledPane();
		myContent.getChildren().addAll(myTimeGoalTitledPane);
	}
	
	private void initializeTimeGoalTitledPane() {
		Spinner<Double> dspinner = new Spinner<>(
				Double.parseDouble(myGoalPromptProperties.getString("MIN_TIME_GOAL")), 
				Double.parseDouble(myGoalPromptProperties.getString("MAX_TIME_GOAL")), 
				Double.parseDouble(myGoalPromptProperties.getString("TIME_INITIAL")),
				Double.parseDouble(myGoalPromptProperties.getString("TIME_STEP")));
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(20));
		Label label = new Label(myLanguageResourceBundle.getString("timeGoal"));
		label.setWrapText(true);
		grid.add(label, 0, 1);
		grid.add(dspinner, 0, 2);
	
		Button applyTimeGoal = new Button(myLanguageResourceBundle.getString("applyTimeGoal"));
		applyTimeGoal.getStyleClass().add("goalButton");
		applyTimeGoal.setOnAction((event) -> {
			PassTime timeGoal = new PassTime(dspinner.getValue());
			myLevel.getAllGoals().add(timeGoal);
			goalSetDialog();
			
		});
		grid.add(applyTimeGoal, 0, 3);
		
		myTimeGoalTitledPane = new TitledPane(myLanguageResourceBundle.getString("timeGoalTitle"), grid);
	}
	
	private void goalSetDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(myLanguageResourceBundle.getString("goalSet"));
		alert.setHeaderText(myLanguageResourceBundle.getString("congrats"));
		alert.setContentText(myLanguageResourceBundle.getString("goalProperlySet"));
		alert.showAndWait();
	}

}
