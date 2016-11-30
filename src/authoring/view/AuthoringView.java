package authoring.view;

import authoring.AuthoringController;
import authoring.view.canvas.CanvasView;
import authoring.view.chat.ChatView;
import authoring.view.components.ComponentPanelFactory;
import authoring.view.components.ComponentPanelView;
import authoring.view.inspector.InspectorView;
import authoring.view.menu.GameMenuFactory;
import authoring.view.menu.GameMenuView;
import javafx.scene.layout.GridPane;

public class AuthoringView extends AbstractView {
    private static final double MENU_HEIGHT = 35;
    private static final double COMPONENT_HEIGHT = 250;
    private static final double INSPECTOR_WIDTH = 300;
    private static final double THRESHOLD_HEIGHT = 650;
    private static final double THRESHOLD_WIDTH = 1200;
    private static final double PADDING = 10;

    private GridPane myGridContent;

    private GameMenuView myGameMenu;
    private InspectorView myInspector;
    private ComponentPanelView myComponents;
    private CanvasView myCanvas;
    private ChatView myChat;

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
        myComponents = ComponentPanelFactory.createComponentPanelView(this.getController());
        myChat = new ChatView(this.getController());
        
        myGridContent.add(myGameMenu.getUI(), 0, 0, 2, 1);
        myGridContent.add(myCanvas.getUI(), 0, 1, 1, 1);
        myGridContent.add(myInspector.getUI(), 1, 1, 1, 1);
        myGridContent.add(myComponents.getUI(), 0, 2, 1, 1);
        myGridContent.add(myChat.getUI(), 1, 2, 1, 1);

        addUI(myGridContent);
        addSubViews(myGameMenu, myCanvas, myInspector, myComponents, myChat);
    }

    @Override
    protected void updateLayoutSelf() {
        if (getWidth() < THRESHOLD_WIDTH) {
            if (myGridContent.getChildren().contains(myInspector.getUI())) {
                myGridContent.getChildren().remove(myInspector.getUI());
                GridPane.setColumnSpan(myCanvas.getUI(), 2);
                GridPane.setColumnSpan(myComponents.getUI(), 2);
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
                myGridContent.add(myComponents.getUI(), 0, 2, 1, 1);
                GridPane.setRowSpan(myCanvas.getUI(), 1);
                GridPane.setRowSpan(myInspector.getUI(), 1);
            }
        }
        if (getWidth() < THRESHOLD_WIDTH || getHeight() < THRESHOLD_HEIGHT) {
        	if (myGridContent.getChildren().contains(myChat.getUI())) {
                myGridContent.getChildren().remove(myChat.getUI());
            }
        } else {
        	if (!myGridContent.getChildren().contains(myChat.getUI())) {
                myGridContent.add(myChat.getUI(), 1, 2, 1, 1);
            }
        }

        double middleHeight = getHeight() < THRESHOLD_HEIGHT ? getHeight() - PADDING - MENU_HEIGHT :
                getHeight() - MENU_HEIGHT - COMPONENT_HEIGHT - (2 * PADDING);

        myGameMenu.setSize(getWidth(), MENU_HEIGHT);
        myCanvas.setSize(getWidth() < THRESHOLD_WIDTH ? getWidth() :
                getWidth() - PADDING - INSPECTOR_WIDTH, middleHeight);
        if (getWidth() >= THRESHOLD_WIDTH) {
            myInspector.setSize(INSPECTOR_WIDTH, middleHeight);
        }
        if (getHeight() >= THRESHOLD_HEIGHT) {
            myComponents.setSize(getWidth() - PADDING - INSPECTOR_WIDTH, COMPONENT_HEIGHT);
        }
        if (getWidth() >= THRESHOLD_WIDTH && getHeight() >= THRESHOLD_HEIGHT) {
        	myChat.setSize(INSPECTOR_WIDTH, COMPONENT_HEIGHT);
        }
    }
}

