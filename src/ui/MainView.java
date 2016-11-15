package ui;

public class MainView extends View {
	
	private NavigatorView navigator;
	private InspectorView inspector;
	private DebugView debugger;
	private ComponentsView components;

	public MainView() {
		navigator = new NavigatorView();
		inspector = new InspectorView();
		debugger = new DebugView();
		components = new ComponentsView();
		addSubViews(navigator, inspector, debugger, components);
	}

	@Override
	protected void layoutSelf() {
		navigator.setPositionAndSize(0, 0, 100, this.getHeight());
		inspector.setPositionAndSize(this.getWidth() - 100, 0, 100, 
				this.getHeight() / 2);
		debugger.setPositionAndSize(100, this.getHeight() - 100, 
				this.getWidth() - 200, 100);
		components.setPositionAndSize(this.getWidth() - 100, this.getHeight() / 2, 
				100, this.getHeight() / 2);
	}
	
}
