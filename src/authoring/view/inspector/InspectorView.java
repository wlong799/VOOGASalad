package authoring.view.inspector;

import authoring.AuthorEnvironment;
import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.updating.IPublisher;
import authoring.updating.ISubscriber;
import authoring.view.AbstractView;
import authoring.view.IView;
import authoring.view.canvas.SpriteView;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class InspectorView extends AbstractView implements ISubscriber {

	private TabPane tabs;
	private InspectorSpriteView inspectorSpriteView;
	private LevelSettingsView settingsView;
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
		setOnChangeTab();
		inspectorSpriteView = new InspectorSpriteView(this.getController());
		settingsView = new LevelSettingsView(this.getController());
		
		AuthorEnvironment env = this.getController().getEnvironment();
		env.getCurrentLevelIndex().addListener(obv -> {
			settingsView.setLevel(env.getCurrentLevel());
		});
		env.getCurrentGameIndex().addListener(obv -> {
			settingsView.setLevel(env.getCurrentLevel());
		});
		settingsView.setLevel(env.getCurrentLevel());
		
		addViewsAsTab("Settings", settingsView);
		addViewsAsTab("Sprite Inspector", inspectorSpriteView);
	}

	@Override
	protected void updateLayoutSelf() {
		tabs.setPrefHeight(this.getHeight());
		tabs.setPrefWidth(this.getWidth());
		for (IView subView : this.getSubViews()) {
			subView.setWidth(this.getWidth());
			subView.setHeight(this.getHeight() - UIConstants.OVERLAP_PIXELS_WITH_CHAT);
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
		newTab.getStyleClass().add("tab");
		newTab.setClosable(false);
		newTab.setContent(view.getUI());
		tabs.getTabs().add(newTab);
		addSubView(view);
	}
	
	private void setOnChangeTab() {
		tabs.getSelectionModel().selectedIndexProperty().addListener(
				(ov, oldVal, newVal) -> {
					if (newVal.intValue() == 0) {
						this.getController().deselectSpriteViews();
					}
				});
	}

}
