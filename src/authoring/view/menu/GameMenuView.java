package authoring.view.menu;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;

/**
 * Menu bar located at top of application. Contains various sub-menus, for opening and closing game workspaces,
 * switching between levels, testing the game, and accessing other game-wide/level-wide content. Created using
 * GameMenuFactory, which adds GameMenus using the addGameMenu function.
 *
 * @author Will Long
 * @version 11/17/16
 */
public class GameMenuView extends AbstractView {

    private MenuBar myMenuView;

    private GameMenuView(AuthoringController controller) {
        super(controller);
    }

    public void addGameMenu(GameMenu menu) {
        myMenuView.getMenus().add(menu.getMenu());
    }

    @Override
    public Parent getUI() {
        return myMenuView;
    }

    @Override
    protected void initUI() {
        myMenuView = new MenuBar();
    }

    @Override
    protected void updateLayoutSelf() {
        myMenuView.setPrefHeight(this.getHeight());
        myMenuView.setPrefWidth(this.getWidth());
    }
}
