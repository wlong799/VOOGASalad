package ui;

public class MainView extends View {
	
	private NavigatorView navigator;
	private InspectorView inspector;
	private ComponentsView components;
	private CanvasView canvas;

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
				this.getHeight() - UIConstants.BOTTOM_HEIGHT, 
				this.getWidth() - UIConstants.LEFT_WIDTH - UIConstants.RIGHT_WIDTH,
				UIConstants.BOTTOM_HEIGHT);
		canvas.setPositionAndSize(
				UIConstants.LEFT_WIDTH,
				0,
				this.getWidth() - UIConstants.LEFT_WIDTH - UIConstants.RIGHT_WIDTH, 
				this.getHeight() - UIConstants.BOTTOM_HEIGHT);
	}
	
}
