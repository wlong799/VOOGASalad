package ui;

public class MainView extends View {
	
	private NavigatorView navigator;
	private InspectorView inspector;
	private ComponentsView components;

	public MainView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	protected void initUI() {
		navigator = new NavigatorView(this.getController());
		inspector = new InspectorView(this.getController());
		components = new ComponentsView(this.getController());
		addSubViews(navigator, inspector, components);
	}

	@Override
	protected void layoutSelf() {
		navigator.setPositionAndSize(0, 0, 100, this.getHeight());
		inspector.setPositionAndSize(this.getWidth() - 100, 0, 100, 
				this.getHeight());
		components.setPositionAndSize(100, this.getHeight() - 100, 
				this.getWidth() - 200, 100);
	}
	
}
