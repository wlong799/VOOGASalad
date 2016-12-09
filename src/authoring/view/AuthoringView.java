package authoring.view;

import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.view.canvas.CanvasView;
import authoring.view.chat.ChatView;
import authoring.view.components.ComponentPanelFactory;
import authoring.view.components.ComponentPanelView;
import authoring.view.inspector.InspectorView;
import authoring.view.menu.GameMenuFactory;
import authoring.view.menu.GameMenuView;
import javafx.scene.layout.GridPane;

public class AuthoringView extends AbstractView {

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
		if (getWidth() < UIConstants.THRESHOLD_WIDTH) {
			myGridContent.getChildren().remove(myInspector.getUI());
			GridPane.setColumnSpan(myCanvas.getUI(), 2);
			GridPane.setColumnSpan(myComponents.getUI(), 2);
		} else {
			if (!myGridContent.getChildren().contains(myInspector.getUI())) {
				myGridContent.add(myInspector.getUI(), 1, 1, 1, 1);
				GridPane.setColumnSpan(myCanvas.getUI(), 1);
			}
		}
		if (getHeight() < UIConstants.THRESHOLD_HEIGHT) {
			myGridContent.getChildren().remove(myComponents.getUI());
			GridPane.setRowSpan(myCanvas.getUI(), 2);
			GridPane.setRowSpan(myInspector.getUI(), 2);
		} else {
			if (!myGridContent.getChildren().contains(myComponents.getUI())) {
				myGridContent.add(myComponents.getUI(), 0, 2, 1, 1);
				GridPane.setRowSpan(myCanvas.getUI(), 1);
				GridPane.setRowSpan(myInspector.getUI(), 1);
			}
		}
		if (getWidth() < UIConstants.THRESHOLD_WIDTH || getHeight() < UIConstants.THRESHOLD_HEIGHT) {
			myGridContent.getChildren().remove(myChat.getUI());
		} else {
			if (!myGridContent.getChildren().contains(myChat.getUI())) {
				myGridContent.add(myChat.getUI(), 1, 2, 1, 1);
			}
		}

		double middleHeight = getHeight() < UIConstants.THRESHOLD_HEIGHT ? getHeight() - - UIConstants.MENU_HEIGHT :
			getHeight() - UIConstants.MENU_HEIGHT - UIConstants.COMPONENT_HEIGHT;

		myGameMenu.setSize(getWidth(), UIConstants.MENU_HEIGHT);
		myCanvas.setSize(getWidth() < UIConstants.THRESHOLD_WIDTH ? getWidth() :
			getWidth() - UIConstants.INSPECTOR_WIDTH, middleHeight);
		if (getWidth() >= UIConstants.THRESHOLD_WIDTH) {
			myInspector.setSize(UIConstants.INSPECTOR_WIDTH, middleHeight);
			myComponents.setSize(getWidth() - UIConstants.INSPECTOR_WIDTH, UIConstants.COMPONENT_HEIGHT);
		}
		else {
			myComponents.setSize(getWidth(), UIConstants.COMPONENT_HEIGHT);
		}
		if (getWidth() >= UIConstants.THRESHOLD_WIDTH && getHeight() >= UIConstants.THRESHOLD_HEIGHT) {
			myChat.setSize(UIConstants.INSPECTOR_WIDTH, UIConstants.COMPONENT_HEIGHT);
		}
	}

}

