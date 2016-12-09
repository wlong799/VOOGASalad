package authoring.goal;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import goal.time.PassTime;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.ResourceBundles;

public class ChangeLevelGoalPrompt {
	private ScrollPane myGoalPane;
	private Stage myGoalStage;
	private VBox myGoalVBox;
	private List<Node> myGoalConfigNodes = new ArrayList<>();
	private AuthoringController myController;
	private ResourceBundle myGoalPromptProperties;	

	public ChangeLevelGoalPrompt(AuthoringController controller) {
		myGoalPromptProperties = ResourceBundles.goalPromptProperties;
		
		myController = controller;
		myGoalStage = new Stage();
		myGoalVBox = new VBox();
		myGoalPane = new ScrollPane();
		myGoalPane.setContent(myGoalVBox);
		Scene goalScene = new Scene(myGoalPane, Double.parseDouble(myGoalPromptProperties.getString("GOAL_PROMPT_FRAME_SIZE")), Double.parseDouble(myGoalPromptProperties.getString("GOAL_PROMPT_FRAME_SIZE")));
		myGoalStage.setScene(goalScene);
		myGoalStage.show();

		levelTypeSelectorInit();
	}

	private void levelTypeSelectorInit() {
		// reach point, beat boss, time goal
		Label selectGoalLabel = new Label("Which type of goal would you like to use?");

		Button reachPointButton = new Button("Reach Point");
		reachPointButton.setOnAction((event) -> {
			myGoalVBox.getChildren().removeAll(myGoalConfigNodes);
			myGoalConfigNodes.clear();
			handleReachPointSelected();
		});

		Button beatBossButton = new Button("Beat Boss");
		beatBossButton.setOnAction((event) -> {
			myGoalVBox.getChildren().removeAll(myGoalConfigNodes);
			myGoalConfigNodes.clear();
			handleBeatBossGoalSelected();
		});

		Button beatTimeButton = new Button("Beat Time");
		beatTimeButton.setOnAction((event) -> {
			myGoalVBox.getChildren().removeAll(myGoalConfigNodes);
			myGoalConfigNodes.clear();
			handleTimeGoalSelected();
		});

		myGoalVBox.getChildren().add(selectGoalLabel);
		myGoalVBox.getChildren().add(beatTimeButton);
		myGoalVBox.getChildren().add(beatBossButton);
		myGoalVBox.getChildren().add(reachPointButton);
	}

	private void handleReachPointSelected() {
		Text instructionText = new Text("Any block can be the goal point of the level. "
				+ "To set a block to be the goal, simply click on it and check the box in the "
				+ "sprite inspector tab labeled 'Assign sprite to be goal point of the level'.");
		
		myGoalConfigNodes.add(instructionText);
		myGoalVBox.getChildren().addAll(myGoalConfigNodes);
	}

	private void handleTimeGoalSelected() {
		Spinner<Double> dspinner = new Spinner<>(Double.parseDouble(myGoalPromptProperties.getString("MIN_TIME_GOAL")), Double.parseDouble(myGoalPromptProperties.getString("MAX_TIME_GOAL")), Double.parseDouble(myGoalPromptProperties.getString("TIME_INITIAL")),Double.parseDouble(myGoalPromptProperties.getString("TIME_STEP")));
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(20));
		grid.add(new Label("Please select the time at which user wins level"), 0, 1);
		grid.add(dspinner, 1, 1);
		
		Button applyTimeGoal = new Button("Apply Time Goal");
		applyTimeGoal.setOnAction((event) -> {
			PassTime timeGoal = new PassTime(dspinner.getValue());
			myController.getEnvironment().getCurrentLevel().getAllGoals().add(timeGoal);
			myGoalStage.close();
		});
		grid.add(applyTimeGoal, 1, 2);
		
		myGoalConfigNodes.add(grid);
		myGoalVBox.getChildren().addAll(myGoalConfigNodes);
	}

	private void handleBeatBossGoalSelected() {
		Text notYetImplemented = new Text("To be implemented.");
		myGoalConfigNodes.add(notYetImplemented);
		myGoalVBox.getChildren().addAll(myGoalConfigNodes);
	}
}
