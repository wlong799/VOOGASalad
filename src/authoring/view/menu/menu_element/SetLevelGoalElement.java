package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.goal.ChangeLevelGoalPrompt;
import authoring.view.menu.AbstractGameMenuElement;

public class SetLevelGoalElement extends AbstractGameMenuElement {
	private AuthoringController myController;
	private static final String MENU_NAME = "Set Level Time Goal";
	
	protected SetLevelGoalElement(AuthoringController controller) {
		super(MENU_NAME, controller);
		myController = controller;
	}

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(event -> {
			new ChangeLevelGoalPrompt(myController);
		});
	}
	
}