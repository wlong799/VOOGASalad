package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.updating.IPublisher;
import authoring.updating.ISubscriber;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import authoring.view.run.TestGameConfiguringView;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class InspectorView extends AbstractView implements ISubscriber {

	private TabPane tabs;
	private InspectorSpriteView inspectorSpriteView;
	private TestGameConfiguringView configureView;
	private SpriteView inspectedSpriteView;
	
	public InspectorView(AuthoringController controller) {
		super(controller);
	}

	@Override
	public Parent getUI() {
		return tabs;
	}
	
	@Override
	public void didUpdate(IPublisher target) {
		if (target instanceof AuthoringController) {
			inspectedSpriteView = ((AuthoringController) target).getSelectedSpriteView();
			updateUI();
		}
	}

	@Override
	protected void initUI() {
		this.getController().addSubscriber(this);
		tabs = new TabPane();
		inspectorSpriteView = new InspectorSpriteView(this.getController());
		configureView = new TestGameConfiguringView(this.getController());
		addViewsAsTab("Physics Settings", configureView);
		addViewsAsTab("Sprite Inspector", inspectorSpriteView);
	}

	@Override
	protected void updateLayoutSelf() {
		tabs.setPrefHeight(this.getHeight());
		tabs.setPrefWidth(this.getWidth());
		for (AbstractView subView : this.getSubViews()) {
			subView.setWidth(this.getWidth());
			subView.setHeight(this.getHeight() - 30);
		}
	}
	
	private void updateUI() {
		inspectorSpriteView.setInspectedSpriteView(inspectedSpriteView);
		//CRITICAL: using raw index number here
		//if anyone adds a new tab, notice the index change!!!
		if (inspectedSpriteView == null) {
			tabs.getSelectionModel().select(0);
		}
		else {
			tabs.getSelectionModel().select(1);
		}
	}

	private void addViewsAsTab(String tabName, AbstractView view) {
		Tab newTab = new Tab(tabName);
		newTab.setClosable(false);
		newTab.setContent(view.getUI());
		tabs.getTabs().add(newTab);
		addSubView(view);
	}

}
