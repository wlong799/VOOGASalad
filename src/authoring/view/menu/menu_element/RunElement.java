package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.menu.AbstractGameMenuElement;

public class RunElement extends AbstractGameMenuElement {

    private RunElement(AuthoringController controller) {
        super(controller.getEnvironment().getLanguageResourceBundle().getString("run"), controller);
    }

	@Override
	protected void setFunctionality() {
		myMenuItem.setOnAction(event -> {
			myController.getTestGameController().showTestGame();
		});
	}

}
