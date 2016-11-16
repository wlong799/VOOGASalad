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
		addSubViews(navigator, inspector, components, canvas);
	}

	@Override
	protected void layoutSelf() {
		navigator.setPositionAndSize(0, 0, 100, this.getHeight());
		inspector.setPositionAndSize(this.getWidth() - 100, 0, 100, 
				this.getHeight());
		components.setPositionAndSize(100, this.getHeight() - 100, 
				this.getWidth() - 200, 100);
		canvas.setPositionAndSize(100, 0, this.getWidth() - 200, 
				this.getHeight() - 100);
	}
	
}
