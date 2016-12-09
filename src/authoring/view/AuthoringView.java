package authoring.view;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.canvas.CanvasView;
import authoring.view.chat.ChatView;
import authoring.view.components.ComponentPanelFactory;
import authoring.view.components.ComponentPanelView;
import authoring.view.inspector.InspectorView;
import authoring.view.menu.GameMenuFactory;
import authoring.view.menu.GameMenuView;
import javafx.scene.layout.GridPane;
import resources.ResourceBundles;

public class AuthoringView extends AbstractView {

	private GridPane myGridContent;

	private GameMenuView myGameMenu;
	private InspectorView myInspector;
	private ComponentPanelView myComponents;
	private CanvasView myCanvas;
	private ChatView myChat;
	private ResourceBundle myCanvasProperties;

	public AuthoringView(AuthoringController controller) {
		super(controller);
		myCanvasProperties = ResourceBundles.canvasProperties;
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
		if (getWidth() < Double.parseDouble(myCanvasProperties.getString("THRESHOLD_WIDTH"))) {
			myGridContent.getChildren().remove(myInspector.getUI());
			GridPane.setColumnSpan(myCanvas.getUI(), 2);
			GridPane.setColumnSpan(myComponents.getUI(), 2);
		} else {
			if (!myGridContent.getChildren().contains(myInspector.getUI())) {
				myGridContent.add(myInspector.getUI(), 1, 1, 1, 1);
				GridPane.setColumnSpan(myCanvas.getUI(), 1);
			}
		}
		if (getHeight() < Double.parseDouble(myCanvasProperties.getString("THRESHOLD_HEIGHT"))) {
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
		if (getWidth() < Double.parseDouble(myCanvasProperties.getString("THRESHOLD_WIDTH")) || getHeight() < Double.parseDouble(myCanvasProperties.getString("THRESHOLD_HEIGHT"))) {
			myGridContent.getChildren().remove(myChat.getUI());
		} else {
			if (!myGridContent.getChildren().contains(myChat.getUI())) {
				myGridContent.add(myChat.getUI(), 1, 2, 1, 1);
			}
		}

		double middleHeight = getHeight() < Double.parseDouble(myCanvasProperties.getString("THRESHOLD_HEIGHT")) ? getHeight() - - Double.parseDouble(myCanvasProperties.getString("THRESHOLD_HEIGHT")) :
			getHeight() - Double.parseDouble(myCanvasProperties.getString("MENU_HEIGHT")) - Double.parseDouble(myCanvasProperties.getString("COMPONENT_HEIGHT"));

		myGameMenu.setSize(getWidth(), Double.parseDouble(myCanvasProperties.getString("MENU_HEIGHT")));
		myCanvas.setSize(getWidth() < Double.parseDouble(myCanvasProperties.getString("THRESHOLD_WIDTH")) ? getWidth() :
			getWidth() - Double.parseDouble(myCanvasProperties.getString("INSPECTOR_WIDTH")), middleHeight);
		if (getWidth() >= Double.parseDouble(myCanvasProperties.getString("THRESHOLD_WIDTH"))) {
			myInspector.setSize(Double.parseDouble(myCanvasProperties.getString("INSPECTOR_WIDTH")), middleHeight);
			myComponents.setSize(getWidth() - Double.parseDouble(myCanvasProperties.getString("INSPECTOR_WIDTH")), Double.parseDouble(myCanvasProperties.getString("COMPONENT_HEIGHT")));
		}
		else {
			myComponents.setSize(getWidth(), Double.parseDouble(myCanvasProperties.getString("COMPONENT_HEIGHT")));
		}
		if (getWidth() >= Double.parseDouble(myCanvasProperties.getString("THRESHOLD_WIDTH")) && getHeight() >= Double.parseDouble(myCanvasProperties.getString("THRESHOLD_HEIGHT"))) {
			myChat.setSize(Double.parseDouble(myCanvasProperties.getString("INSPECTOR_WIDTH")), Double.parseDouble(myCanvasProperties.getString("COMPONENT_HEIGHT")));
		}
	}

}

