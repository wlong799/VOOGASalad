package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;

public class RunElement extends AbstractGameMenuElement {

	private static final String MENU_NAME = "Run";

    private RunElement(AuthoringController controller) {
        super(MENU_NAME, controller);
    }

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(event -> {
			myController.getTestGameController().showTestGame();
		});
	}

}
