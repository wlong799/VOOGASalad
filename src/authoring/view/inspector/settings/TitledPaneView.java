package authoring.view.inspector.settings;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import authoring.AuthoringController;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.KeyEvent;
import game_object.level.Level;
import goal.time.PassTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.ResourceBundles;

public class TitledPaneView extends AbstractSettingsView {

	private TitledPane myTimeGoalTitledPane, myPointGoalTitledPane, myBeatBossGoalTitledPane;
	private Level myLevel;
	private ResourceBundle myGoalPromptProperties;	
	private ResourceBundle myLanguageResourceBundle;
	
	public TitledPaneView(AuthoringController controller, Level level) {
		super(controller);
		
		myGoalPromptProperties = ResourceBundles.goalPromptProperties;
		myLevel = level;
	}
	
	@Override
    protected void initUI() {
        super.initUI();
        myLanguageResourceBundle = super.getController().getEnvironment().getLanguageResourceBundle();
        //myContent.getChildren().add(myTitledPane);
    }

	@Override
	public void initializeSettings() {
		initializeTimeGoalTitledPane();
		initializePointGoalTitledPane();
		myContent.getChildren().addAll(myTimeGoalTitledPane, myPointGoalTitledPane);
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
		grid.add(new Label(myLanguageResourceBundle.getString("timeGoal")), 0, 1);
		grid.add(dspinner, 0, 2);
	
		Button applyTimeGoal = new Button(myLanguageResourceBundle.getString("applyTimeGoal"));
		applyTimeGoal.getStyleClass().add("goalButton");
		applyTimeGoal.setOnAction((event) -> {
			PassTime timeGoal = new PassTime(dspinner.getValue());
			myLevel.getAllGoals().add(timeGoal);
		});
		grid.add(applyTimeGoal, 0, 3);
		
		myTimeGoalTitledPane = new TitledPane(myLanguageResourceBundle.getString("timeGoal"), grid);
	}
	
	private void initializePointGoalTitledPane() {
		TableView<Map.Entry<String, String>> pointGoals = new TableView<>();
		TableColumn<Map.Entry<String, String>, String> column1 = new TableColumn<>(myLanguageResourceBundle.getString("hero"));
        column1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        TableColumn<Map.Entry<String, String>, String> column2 = new TableColumn<>(myLanguageResourceBundle.getString("goal"));
        column2.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        
        pointGoals.getColumns().addAll(column1, column2);
        myContent.getChildren().add(pointGoals);
        myPointGoalTitledPane = new TitledPane(myLanguageResourceBundle.getString("pointGoal"), pointGoals);
	
//        pointGoals.setOnMousePressed(event -> {
//            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
//                Entry<String, String> entry = pointGoals.getSelectionModel().getSelectedItem();
//                Level currentLevel = this.getController().getEnvironment().getCurrentLevel();
//                ActionTrigger currentTrigger =
//                        currentLevel.getTriggerWithSpriteAndAction(mySprite, action);
//                if (currentTrigger == null) {
//                    currentTrigger = new ActionTrigger(new KeyEvent(null), mySprite, action);
//                    currentLevel.getAllTriggers().add(currentTrigger);
//                }
//                KeyCode code = getKeyDialog(currentTrigger);
//                if (code == null) {
//                    currentLevel.getAllTriggers().remove(currentTrigger);
//                    myEntryMap.put(action.toString(), "None");
//                } else {
//                    currentTrigger.setEvent(new KeyEvent(code));
//                    myEntryMap.put(action.toString(), code.toString());
//                }
//                myItems.clear();
//                myItems.addAll(myEntryMap.entrySet());
//            }
//        });
	
	}

}
