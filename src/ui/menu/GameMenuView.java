package ui.menu;

import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import ui.AuthoringController;
import ui.View;

/**
 * Menu bar located at top of application. Contains various sub-menus, for opening and closing game workspaces,
 * switching between levels, testing the game, and accessing other game-wide/level-wide content. Created using
 * GameMenuFactory, which adds GameMenus using the addGameMenu function.
 *
 * @author Will Long
 * @version 11/17/16
 */
public class GameMenuView extends View {

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
    protected void layoutSelf() {
        return;
    }
}
