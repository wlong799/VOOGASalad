package authoring.view;

import authoring.AuthoringController;
import authoring.View;
import authoring.constants.UIConstants;
import authoring.view.canvas.CanvasView;
import authoring.view.components.ComponentsView;
import authoring.view.menu.GameMenuFactory;
import authoring.view.menu.GameMenuView;

public class MainView extends View {
	
	private GameMenuView navigator;
	private InspectorView inspector;
	private ComponentsView components;
	private CanvasView canvas;
	private double componentsHeight;
	private double initialHeight = 0;

	public MainView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	protected void initUI() {
		navigator = GameMenuFactory.createGameMenuView(this.getController());
		inspector = new InspectorView(this.getController());
		components = new ComponentsView(this.getController());
		canvas = new CanvasView(this.getController());
		addSubViews(navigator, inspector, canvas, components);
	}

	@Override
	protected void layoutSelf() {
		recalculateSizes();
		navigator.setPositionAndSize(
				0,
				0,
				this.getWidth(),
				UIConstants.TOP_HEIGHT);
		inspector.setPositionAndSize(
				this.getWidth() - UIConstants.RIGHT_WIDTH,
				UIConstants.TOP_HEIGHT,
				UIConstants.RIGHT_WIDTH,
				this.getHeight());
		components.setPositionAndSize(
				0, 
				this.getHeight() - componentsHeight, 
				this.getWidth() - UIConstants.RIGHT_WIDTH,
				componentsHeight);
		canvas.setPositionAndSize(
				0,
				UIConstants.TOP_HEIGHT,
				this.getWidth() - UIConstants.RIGHT_WIDTH, 
				this.getHeight() - componentsHeight - UIConstants.TOP_HEIGHT);
	}
	
	private void recalculateSizes() {
		if (this.getHeight() > initialHeight) {
			initialHeight = this.getHeight();
		}
		componentsHeight = Math.max(
			this.getHeight() / initialHeight * UIConstants.BOTTOM_HEIGHT,
			UIConstants.COMPONENTS_MIN_HEIGHT);
	}
	
}
