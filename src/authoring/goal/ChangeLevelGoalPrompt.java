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
	private ScrollPane goalPane;
	private Stage goalStage;
	private VBox goalVBox;
	private List<Node> goalConfigNodes = new ArrayList<>();
	private AuthoringController myController;
	private ResourceBundle goalPromptProperties;	

	public ChangeLevelGoalPrompt(AuthoringController controller) {
		goalPromptProperties = ResourceBundles.goalPromptProperties;
		
		myController = controller;
		goalStage = new Stage();
		goalVBox = new VBox();
		goalPane = new ScrollPane();
		goalPane.setContent(goalVBox);
		Scene goalScene = new Scene(goalPane, Double.parseDouble(goalPromptProperties.getString("GOAL_PROMPT_FRAME_SIZE")), Double.parseDouble(goalPromptProperties.getString("GOAL_PROMPT_FRAME_SIZE")));
		goalStage.setScene(goalScene);
		goalStage.show();

		levelTypeSelectorInit();
	}

	private void levelTypeSelectorInit() {
		// reach point, beat boss, time goal
		Label selectGoalLabel = new Label("Which type of goal would you like to use?");

		Button reachPointButton = new Button("Reach Point");
		reachPointButton.setOnAction((event) -> {
			goalVBox.getChildren().removeAll(goalConfigNodes);
			goalConfigNodes.clear();
			handleReachPointSelected();
		});

		Button beatBossButton = new Button("Beat Boss");
		beatBossButton.setOnAction((event) -> {
			goalVBox.getChildren().removeAll(goalConfigNodes);
			goalConfigNodes.clear();
			handleBeatBossGoalSelected();
		});

		Button beatTimeButton = new Button("Beat Time");
		beatTimeButton.setOnAction((event) -> {
			goalVBox.getChildren().removeAll(goalConfigNodes);
			goalConfigNodes.clear();
			handleTimeGoalSelected();
		});

		goalVBox.getChildren().add(selectGoalLabel);
		goalVBox.getChildren().add(beatTimeButton);
		goalVBox.getChildren().add(beatBossButton);
		goalVBox.getChildren().add(reachPointButton);
	}

	private void handleReachPointSelected() {
		Text instructionText = new Text("Any block can be the goal point of the level. "
				+ "To set a block to be the goal, simply click on it and check the box in the "
				+ "sprite inspector tab labeled 'Assign sprite to be goal point of the level'.");
		
		goalConfigNodes.add(instructionText);
		goalVBox.getChildren().addAll(goalConfigNodes);
	}

	private void handleTimeGoalSelected() {
		Spinner<Double> dspinner = new Spinner<>(Double.parseDouble(goalPromptProperties.getString("MIN_TIME_GOAL")), Double.parseDouble(goalPromptProperties.getString("MAX_TIME_GOAL")), Double.parseDouble(goalPromptProperties.getString("TIME_INITIAL")),Double.parseDouble(goalPromptProperties.getString("TIME_STEP")));
		
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
			goalStage.close();
		});
		grid.add(applyTimeGoal, 1, 2);
		
		goalConfigNodes.add(grid);
		goalVBox.getChildren().addAll(goalConfigNodes);
	}

	private void handleBeatBossGoalSelected() {
		Text notYetImplemented = new Text("To be implemented.");
		goalConfigNodes.add(notYetImplemented);
		goalVBox.getChildren().addAll(goalConfigNodes);
	}
}
