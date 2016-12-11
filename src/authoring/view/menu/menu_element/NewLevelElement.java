package authoring.view.menu.menu_element;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.ui.DialogFactory;
import authoring.view.menu.AbstractGameMenuElement;
import authoring.view.menu.GameAdder;

/**
 * Adds a new level to the game.
 *
 * @author Will Long, Bill Yu
 * @version 11/28/16
 */
public class NewLevelElement extends AbstractGameMenuElement {

    private NewLevelElement(AuthoringController controller) {
        super(controller.getEnvironment().getLanguageResourceBundle().getString("newLevel"), controller);
    }

    @Override
    protected void setFunctionality() {
        GameAdder adder = new GameAdder();
        myMenuItem.setOnAction(event -> {
            if (adder.addLevel(myController.getEnvironment())) {
            } else {
                showDuplicateIDError();
            }
        });
    }

    private void showDuplicateIDError() {
        ResourceBundle rs = myController.getEnvironment().getLanguageResourceBundle();
    	DialogFactory.showErrorDialog(
                rs.getString("error"),
                rs.getString("errorAdding"),
                rs.getString("alreadyUsed")
        );
    }
}
