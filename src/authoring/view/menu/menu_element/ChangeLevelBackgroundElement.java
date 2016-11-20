package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;

public class ChangeLevelBackgroundElement extends AbstractGameMenuElement{

	private static final String MENU_NAME = "Change Level Background";
	 // TODO: link this to resources file
	
	protected ChangeLevelBackgroundElement(AuthoringController controller) {
		super(MENU_NAME, controller);
	}

	@Override
	protected void setFunctionality() {
		myController.getEnvironment();
		myMenuItem.setOnAction((event) -> {
			// TODO: fill this out
		});
	}
}
