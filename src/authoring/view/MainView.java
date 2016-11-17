package authoring.view;

import authoring.AuthoringController;
import authoring.View;
import authoring.constants.UIConstants;

public class MainView extends View {
	
	private NavigatorView navigator;
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
		navigator = new NavigatorView(this.getController());
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
				UIConstants.LEFT_WIDTH,
				this.getHeight());
		inspector.setPositionAndSize(
				this.getWidth() - UIConstants.RIGHT_WIDTH,
				0,
				UIConstants.RIGHT_WIDTH,
				this.getHeight());
		components.setPositionAndSize(
				UIConstants.LEFT_WIDTH, 
				this.getHeight() - componentsHeight, 
				this.getWidth() - UIConstants.LEFT_WIDTH - UIConstants.RIGHT_WIDTH,
				componentsHeight);
		canvas.setPositionAndSize(
				UIConstants.LEFT_WIDTH,
				0,
				this.getWidth() - UIConstants.LEFT_WIDTH - UIConstants.RIGHT_WIDTH, 
				this.getHeight() - componentsHeight);
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
