package authoring.view;

import authoring.AuthoringController;
import authoring.View;
import authoring.view.canvas.CanvasView;
import authoring.view.components.ComponentsView;
import authoring.view.inspector.InspectorView;
import authoring.view.menu.GameMenuFactory;
import authoring.view.menu.GameMenuView;
import javafx.scene.layout.GridPane;

public class AuthoringView extends View {
    private static final double MENU_HEIGHT = 35;
    private static final double COMPONENT_HEIGHT = 250;
    private static final double INSPECTOR_WIDTH = 300;
    private static final double THRESHOLD_HEIGHT = 650;
    private static final double THRESHOLD_WIDTH = 1200;
    private static final double PADDING = 10;

    private GridPane myGridContent;

    private GameMenuView myGameMenu;
    private InspectorView myInspector;
    private ComponentsView myComponents;
    private CanvasView myCanvas;

    public AuthoringView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        myGridContent = new GridPane();
        myGridContent.setHgap(PADDING);
        myGridContent.setVgap(PADDING);

        myGameMenu = GameMenuFactory.createGameMenuView(this.getController());
        myCanvas = new CanvasView(this.getController());
        myInspector = new InspectorView(this.getController());
        myComponents = new ComponentsView(this.getController());

        myGridContent.add(myGameMenu.getUI(), 0, 0, 2, 1);
        myGridContent.add(myCanvas.getUI(), 0, 1, 1, 1);
        myGridContent.add(myInspector.getUI(), 1, 1, 1, 1);
        myGridContent.add(myComponents.getUI(), 0, 2, 2, 1);

        addUI(myGridContent);
    }

    @Override
    protected void layoutSelf() {
        if (getWidth() < THRESHOLD_WIDTH) {
            if (myGridContent.getChildren().contains(myInspector.getUI())) {
                myGridContent.getChildren().remove(myInspector.getUI());
                GridPane.setColumnSpan(myCanvas.getUI(), 2);
            }
        } else {
            if (!myGridContent.getChildren().contains(myInspector.getUI())) {
                myGridContent.add(myInspector.getUI(), 1, 1, 1, 1);
                GridPane.setColumnSpan(myCanvas.getUI(), 1);
            }
        }
        if (getHeight() < THRESHOLD_HEIGHT) {
            if (myGridContent.getChildren().contains(myComponents.getUI())) {
                myGridContent.getChildren().remove(myComponents.getUI());
                GridPane.setRowSpan(myCanvas.getUI(), 2);
                GridPane.setRowSpan(myInspector.getUI(), 2);
            }
        } else {
            if (!myGridContent.getChildren().contains(myComponents.getUI())) {
                myGridContent.add(myComponents.getUI(), 0, 2, 2, 1);
                GridPane.setRowSpan(myCanvas.getUI(), 1);
                GridPane.setRowSpan(myInspector.getUI(), 1);
            }
        }

        double middleHeight = getHeight() < THRESHOLD_HEIGHT ? getHeight() - PADDING - MENU_HEIGHT :
                getHeight() - MENU_HEIGHT - COMPONENT_HEIGHT - (2 * PADDING);

        myGameMenu.setPositionAndSize(0, 0, getWidth(), MENU_HEIGHT);
        myCanvas.setPositionAndSize(0, 0, getWidth() < THRESHOLD_WIDTH ? getWidth() :
                getWidth() - PADDING - INSPECTOR_WIDTH, middleHeight);
        if (getWidth() >= THRESHOLD_WIDTH) {
            myInspector.setPositionAndSize(0, 0, INSPECTOR_WIDTH, middleHeight);
        }
        if (getHeight() >= THRESHOLD_HEIGHT) {
            myComponents.setPositionAndSize(0, 0, getWidth(), COMPONENT_HEIGHT);
        }
        myGameMenu.layout();
        myCanvas.layout();
        myInspector.layout();
        myComponents.layout();
    }
}

