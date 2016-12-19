package authoring.view.menu;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;

/**
 * View object that provides a menu bar located at the top of the application. Provides functionality to add various
 * container menus to the bar. Due to complicated process of initializing a menu bar with many different menus, creation
 * is handled through the GameMenuFactory class, which allows users to specify which menu element classes they wish to
 * load into the bar.
 *
 * @author Will Long
 * @version 12/18/16
 */
public class GameMenuView extends AbstractView {
    private MenuBar myMenuView;

    /**
     * Private constructor to force (or at least heavily push for) creation only through the GameMenuFactory class.
     *
     * @param controller is the top-level controller of authoring environment.
     */
    private GameMenuView(AuthoringController controller) {
        super(controller);
    }

    /**
     * Adds a new top-level menu to the view.
     *
     * @param menu is the container menu to add to the top bar of the menu.
     */
    public void addGameMenu(AbstractGameMenu menu) {
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
        myMenuView.setPrefHeight(getHeight());
        myMenuView.setPrefWidth(getWidth());
    }
}
