package authoring.view.inspector.settings.sprite;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.character.Hero;
import game_object.core.ISprite;
import game_object.level.Level;
import goal.position.ReachPointGoal;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ReachPointGoalConfiguringView extends AbstractView {
	VBox myGoalVBox;
	CheckBox myReachPointCheckBox;
	AuthoringController myController;

	public ReachPointGoalConfiguringView(AuthoringController controller) {
		super(controller);
		myController = controller;
	}

	public void setUpReachPointGoalCheckBox(ISprite sprite) {
		myReachPointCheckBox = new CheckBox("Assign sprite to be goal point of the level");
		myReachPointCheckBox.selectedProperty().addListener((ov, oldVal, newVal) -> {
			Level current = myController.getEnvironment().getCurrentLevel();
			for (Hero hero : current.getHeros()) {
				ReachPointGoal reachGoal = new ReachPointGoal(hero, sprite);
				current.getAllGoals().add(reachGoal);
			}
		});

		myGoalVBox = new VBox();
		Label goalLevel = new Label("Goals");
		goalLevel.setLabelFor(myReachPointCheckBox);
		myGoalVBox.getChildren().add(goalLevel);
		myGoalVBox.getChildren().add(myReachPointCheckBox);
	}

	public Parent getUI() {
		return myGoalVBox;
	}

	@Override
	protected void initUI() {
	}

	@Override
	protected void updateLayoutSelf() {	
	}

}
