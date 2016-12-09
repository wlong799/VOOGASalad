package authoring.view.inspector.settings;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import game_object.level.Level;
import goal.time.PassTime;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import resources.ResourceBundles;

public class TitledPaneView extends AbstractSettingsView {

	private TitledPane myTimeGoalTitledPane, myPointGoalTitledPane, myBeatBossGoalTitledPane;
	private Level myLevel;
	private ResourceBundle myGoalPromptProperties;	
	
	public TitledPaneView(AuthoringController controller, Level level) {
		super(controller);
		myGoalPromptProperties = ResourceBundles.goalPromptProperties;
		myLevel = level;
	}
	
	@Override
    protected void initUI() {
        super.initUI();
        
        //myContent.getChildren().add(myTitledPane);
    }

	@Override
	public void initializeSettings() {
		initializeTimeGoalTitledPane();
		myContent.getChildren().add(myTimeGoalTitledPane);
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
		grid.add(new Label("Please select the time at which user wins level"), 0, 1);
		grid.add(dspinner, 0, 2);
	
		Button applyTimeGoal = new Button("Apply Time Goal");
		applyTimeGoal.setOnAction((event) -> {
			PassTime timeGoal = new PassTime(dspinner.getValue());
			myLevel.getAllGoals().add(timeGoal);
		});
		grid.add(applyTimeGoal, 0, 3);
		
		myTimeGoalTitledPane = new TitledPane("Time Goal", grid);
	}
	
	private void initializePointGoalTitledPane() {
		
	}
	
	private void initializeBeatBossGoalTitledPane() {
		
	}

}
